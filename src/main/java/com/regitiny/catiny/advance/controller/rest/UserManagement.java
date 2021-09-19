package com.regitiny.catiny.advance.controller.rest;

import com.regitiny.catiny.service.dto.MasterUserDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * BASE_PATH: {@value BASE_PATH}
 * <p>
 * user manage api
 */
@RequestMapping(UserManagement.BASE_PATH)
public interface UserManagement
{
  String BASE_PATH = "/api/o/users"; //NOSONAR


  /**
   * POST /api/o/users : fetch current MasterUser
   *
   * @return MasterUserDTO (status code 200) or Not Found (status code 404)
   */
  @ApiOperation(value = "fetch current MasterUser ",
    nickname = "fetchCurrentMasterUser",
    response = MasterUserDTO.class,
    authorizations = {@Authorization(value = "jwt")},
    tags = {"user-management",})
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Exist current MasterUser", response = MasterUserDTO.class),
    @ApiResponse(code = 404, message = "Not found MasterUser")})
  @GetMapping(value = "",
    produces = {"application/json"})
  default ResponseEntity<MasterUserDTO> fetchCurrentMasterUser()
  {
    return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
  }


  @GetMapping("/_search")
  ResponseEntity<List<MasterUserDTO>> searchMasterUser(@RequestParam String query, Pageable pageable);
}
