package com.regitiny.catiny.advance.controller.rest;

import com.regitiny.catiny.service.dto.MessageGroupDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * các api quản lý message
 */
@RequestMapping("/api/o/messages")
public interface MessageManagement
{
  /**
   * GET /api/o/messages/message-groups/joined/all
   */
  @GetMapping("/message-groups/joined/all")
  default ResponseEntity<List<MessageGroupDTO>> getAllMessageGroupsJoined(Pageable pageable)
  {
    return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
  }
}
