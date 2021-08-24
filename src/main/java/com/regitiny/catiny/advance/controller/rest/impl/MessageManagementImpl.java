package com.regitiny.catiny.advance.controller.rest.impl;

import com.regitiny.catiny.advance.controller.rest.MessageManagement;
import com.regitiny.catiny.advance.service.MessageContentAdvanceService;
import com.regitiny.catiny.advance.service.MessageGroupAdvanceService;
import com.regitiny.catiny.service.dto.MessageGroupDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.PaginationUtil;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
public class MessageManagementImpl implements MessageManagement
{
  private final MessageGroupAdvanceService messageGroupAdvanceService;
  private final MessageContentAdvanceService messageContentAdvanceService;

  @Override
  public ResponseEntity<List<MessageGroupDTO>> getAllMessageGroupsJoined(Pageable pageable)
  {
    log.debug("request get all messages group user joined , pageable = {}", pageable);
    var page = messageGroupAdvanceService.getAllMessageGroupsJoined(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }
}
