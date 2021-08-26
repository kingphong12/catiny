package com.regitiny.catiny.advance.controller.rest;

import com.regitiny.catiny.service.dto.MessageContentDTO;
import com.regitiny.catiny.service.dto.MessageGroupDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * các api quản lý message
 */
@RequestMapping(MessageManagement.BASE_PATH)
public interface MessageManagement
{
  String BASE_PATH = "/api/o/messages";


  /**
   * GET /api/o/messages/message-groups/joined
   */
  @GetMapping("/groups/joined")
  default ResponseEntity<List<MessageGroupDTO>> getAllMessageGroupsJoined(Pageable pageable)
  {
    return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
  }


  /**
   * GET /api/o/messages/groups/{uuid}/contents
   */
  @GetMapping("/groups/{uuid}/contents")
  ResponseEntity<List<MessageContentDTO>> getMessageContentInMessageGroup(
    @PathVariable UUID uuid,
    Pageable pageable);


  /**
   * GET /api/o/messages/groups
   */
  @PostMapping("/groups/")
  ResponseEntity<MessageGroupDTO> createMessageGroup(
    @RequestParam List<UUID> userIds,
    @RequestParam(required = false) String desiredName);
}
