package com.regitiny.catiny.advance.controller.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * BASE_PATH: {@value BASE_PATH}
 * <p>
 * admin apis to manage the site
 */
@RequestMapping(AdminManagement.BASE_PATH)
public interface AdminManagement
{
  String BASE_PATH = "/api/admin"; //NOSONAR


  @PatchMapping("/elasticsearch/reindex/_management")
  ResponseEntity<String> elasticsearchReindex(@RequestParam(required = false, defaultValue = "true") Boolean reindexAll);

}
