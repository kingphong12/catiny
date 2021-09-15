package com.regitiny.catiny.advance.controller.impl;

import com.regitiny.catiny.advance.controller.rest.AdminManagement;
import com.regitiny.catiny.advance.service.AdminService;
import com.regitiny.catiny.common.utils.StringPool;
import io.github.classgraph.ClassGraph;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
public class AdminManagementImpl implements AdminManagement
{
  private final AdminService adminService;

  @Override
  public ResponseEntity<String> elasticsearchReindex(Boolean reindexAll)
  {
    final var packageAdvanceSearch = "com.regitiny.catiny.advance.repository.search";
    var succeed = new JSONArray();
    var failed = new JSONArray();
    try (var scanResult = new ClassGraph().acceptPackages(packageAdvanceSearch).enableClassInfo().scan())
    {
      var classNames = scanResult.getAllClasses().getNames();
      classNames.parallelStream().filter(className -> className.matches(packageAdvanceSearch + ".[A-Z][a-z][\\w]+AdvanceSearch"))
        .map(s -> s
          .replace(packageAdvanceSearch + StringPool.PERIOD, StringPool.BLANK)
          .replace("AdvanceSearch", StringPool.BLANK))
        .forEach(entityName ->
        {
          if (Boolean.TRUE.equals(adminService.reindexEntity(entityName)))
            succeed.put(entityName);
          else
            failed.put(entityName);
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
