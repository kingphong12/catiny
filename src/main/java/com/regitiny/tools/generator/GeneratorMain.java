package com.regitiny.tools.generator;

import com.squareup.javapoet.JavaFile;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;

@Log4j2
public class GeneratorMain
{
  private static final String JSON_KEY_ENTITIES = "entities";
  private static final String JSON_KEY_GENERATED = "generated";

  public static void main(String[] args)
  {
    var thisClassPath = GeneratorMain.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    var projectPath = thisClassPath.replace("/build/classes/java/main/", "");
    generate(projectPath, projectPath + "2");
  }

  public static void generate(String thisProjectPath, String outputProjectPath)
  {
    try (
      var fg = new FileInputStream(thisProjectPath + "/.generate/generate-advance.json");
      var fj = new FileInputStream(thisProjectPath + "/.yo-rc.json"))
    {
      var allBytesGeneratedInfo = fg.readAllBytes();
      var allBytesJhipster = fj.readAllBytes();
      var jsonJhipsterRoot = new JSONObject(new String(allBytesJhipster));
      var jsonGenRoot = new JSONObject(new String(allBytesGeneratedInfo));
      var entitiesGenerated = new JSONArray();

      var entityCanGenerate = new HashSet<String>();
      Optional.of(jsonJhipsterRoot)
        .filter(jsonObject -> jsonObject.has("generator-jhipster"))
        .map(jsonObject -> jsonObject.getJSONObject("generator-jhipster"))
        .filter(jsonObject -> jsonObject.has(JSON_KEY_ENTITIES))
        .map(jsonObject -> jsonObject.getJSONArray(JSON_KEY_ENTITIES))
        .ifPresent(objects ->
        {
          for (var i = 0; i < objects.length(); i++)
            entityCanGenerate.add(objects.getString(i));
        });
      Optional.of(jsonGenRoot)
        .filter(jsonObject -> jsonObject.has(JSON_KEY_ENTITIES) && !entityCanGenerate.isEmpty())
        .map(jsonObject -> jsonObject.getJSONObject(JSON_KEY_ENTITIES))
        .ifPresent(objects ->
        {
          var entities = objects.keys();
          while (entities.hasNext())
          {
            var entity = entities.next();
            entitiesGenerated.put(entity);
            entityCanGenerate.remove(entity);
          }
        });
      entityCanGenerate.forEach(s -> log.debug("entity has generate : {}", s));
      entityCanGenerate.forEach(entityName ->
      {
        var result = GenerateEntityAdvanceUtils.Generate(entityName, outputProjectPath);
        var entities = jsonGenRoot.getJSONObject(JSON_KEY_ENTITIES);
        var json = entities.has(entityName)
          ? jsonGenRoot.getJSONObject(JSON_KEY_ENTITIES).getJSONObject(entityName)
          : new JSONObject();

        var generated = json.has(JSON_KEY_GENERATED)
          ? json.getJSONObject(JSON_KEY_GENERATED)
          : new JSONObject();
        result._1().stream().map(JavaFile::toJavaFileObject)
          .forEach(javaFileObject -> generated.put(javaFileObject.getName(), true));
        result._2().stream().map(JavaFile::toJavaFileObject)
          .forEach(javaFileObject -> generated.put(javaFileObject.getName(), false));

        json.put(JSON_KEY_GENERATED, generated);
        entities.put(entityName, json);
        jsonGenRoot.put(JSON_KEY_ENTITIES, entities);
      });
      try (var fgWriter = new FileOutputStream(thisProjectPath + "/.generate/generate-advance.json"))
      {
        fgWriter.write(jsonGenRoot.toString().getBytes());
        fgWriter.flush();
      }
    }
    catch (IOException e)
    {
      log.warn("err IOException", e);
    }
    log.info("generate done. file output in : " + outputProjectPath);
  }
}
