package com.regitiny.catiny.advance.controller.impl;

import com.regitiny.catiny.advance.controller.rest.AdminManagement;
import com.regitiny.catiny.advance.service.mapper.EntityAdvanceMapper;
import com.regitiny.catiny.common.utils.StringPool;
import com.regitiny.catiny.util.ApplicationContextUtil;
import io.github.classgraph.ClassGraph;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
public class AdminManagementImpl implements AdminManagement
{
  @Override
  @SuppressWarnings("unchecked")
  @Transactional
  public ResponseEntity<String> elasticsearchReindex(Boolean reindexAll)
  {
    final var packageAdvanceSearch = "com.regitiny.catiny.advance.repository.search";
    final var packageAdvanceRepository = "com.regitiny.catiny.advance.repository";
    var succeed = new JSONArray();
    var failed = new JSONArray();
    try (var scanResult = new ClassGraph().acceptPackages(packageAdvanceSearch).enableClassInfo().scan())
    {
      var classNames = scanResult.getAllClasses().getNames();
      classNames.stream().filter(className -> className.matches(packageAdvanceSearch + ".[A-Z][a-z][\\w]+AdvanceSearch"))
        .forEach(advanceSearchName ->
        {
          var entityName = advanceSearchName
            .replace(packageAdvanceSearch + StringPool.PERIOD, StringPool.BLANK)
            .replace("AdvanceSearch", StringPool.BLANK);
          var advanceRepositoryName = packageAdvanceRepository + StringPool.PERIOD + entityName + "AdvanceRepository";
          var advanceMapperName = "com.regitiny.catiny.advance.service.mapper." + entityName + "AdvanceMapper";
          try
          {
            var advanceMapper = (EntityAdvanceMapper<?, ?, Object>) ApplicationContextUtil.getApplicationContext().getBean(Class.forName(advanceMapperName));
            var advanceRepository = (CrudRepository<?, ?>) ApplicationContextUtil.getApplicationContext().getBean(Class.forName(advanceRepositoryName));
            var advanceSearch = (CrudRepository<Object, ?>) ApplicationContextUtil.getApplicationContext().getBean(Class.forName(advanceSearchName));
            advanceSearch.deleteAll();
            advanceSearch.saveAll(advanceMapper.cleanEntity((List<Object>) advanceRepository.findAll()));
            succeed.put(entityName);
            log.info("reindex all entity : {} Done!", entityName);
          }
          catch (Exception e)
          {
            failed.put(entityName);
            log.warn("error when reindex entity : {}", entityName, e);
          }
        });
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(new JSONObject()
      .put("succeed", succeed)
      .put("failed", failed)
      .put("total", succeed.length() + failed.length())
      .put("totalSucceed", succeed.length())
      .put("totalFailed", failed.length())
      .toString());
  }
}
