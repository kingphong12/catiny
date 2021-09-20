package com.regitiny.catiny.advance.controller.impl;

import com.regitiny.catiny.advance.controller.rest.UserManagement;
import com.regitiny.catiny.advance.service.MasterUserAdvanceService;
import com.regitiny.catiny.service.dto.MasterUserDTO;
import com.regitiny.catiny.util.MasterUserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.PaginationUtil;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
public class UserManagementImpl implements UserManagement
{
  private final MasterUserAdvanceService masterUserService;

  @Override
  public ResponseEntity<MasterUserDTO> fetchCurrentMasterUser()
  {
    return MasterUserUtils.getCurrentMasterUserDTO()
      .map(ResponseEntity::ok)
      .getOrElse(ResponseEntity.notFound()::build);
  }

  @Override
  public ResponseEntity<List<MasterUserDTO>> searchMasterUser(String query, Pageable pageable)
  {
    var page = masterUserService.searchMasterUser(query, pageable);
    var headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }
}
