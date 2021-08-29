package com.regitiny.catiny.advance.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * BASE_PATH: {@value BASE_PATH}
 * <p>
 * file management api
 */
@RequestMapping("/api/o/files")
public interface FileManagement
{
  String BASE_PATH = "/api/o/files"; //NOSONAR
}
