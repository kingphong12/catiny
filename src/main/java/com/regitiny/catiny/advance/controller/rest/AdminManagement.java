package com.regitiny.catiny.advance.controller.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * các api của admin để quản lý trang web
 */
@RequestMapping(AdminManagement.BASE_PATH)
public interface AdminManagement
{
  String BASE_PATH = "/api/admin";


  @PatchMapping("/elasticsearch/reindex/_management")
  ResponseEntity<String> elasticsearchReindex(@RequestParam(required = false, defaultValue = "true") Boolean reindexAll);

}
