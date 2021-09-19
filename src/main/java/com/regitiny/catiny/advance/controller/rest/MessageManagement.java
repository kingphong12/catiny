package com.regitiny.catiny.advance.controller.rest;

import com.regitiny.catiny.service.dto.MessageContentDTO;
import com.regitiny.catiny.service.dto.MessageGroupDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

/**
 * BASE_PATH: {@value BASE_PATH}
 * <p>
 * message management api
 */
@RequestMapping(MessageManagement.BASE_PATH)
public interface MessageManagement
{
  String BASE_PATH = "/api/o/messages"; //NOSONAR


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
   * POST /api/o/messages/groups
   */
  @PostMapping("/groups")
  ResponseEntity<MessageGroupDTO> createMessageGroup(
    @RequestParam List<UUID> userIds,
    @RequestParam(required = false) String desiredName);


  /**
   * GET /api/o/messages/groups/{uuid}/master-users/_public
   *
   * @param messageGroupId (UUID)
   * @return List<MasterUserDTO>
   */
  @GetMapping("/groups/{messageGroupId}/master-users/_public")
  ResponseEntity<Object> getMasterUsersDetailsPublicByMessageGroupId(@PathVariable UUID messageGroupId);


  @PostMapping("/groups/{messageGroupId}/contents")
  ResponseEntity<MessageContentDTO> sendContentToGroup(
    @PathVariable UUID messageGroupId,
    @RequestParam String content,
    @RequestPart(required = false) List<MultipartFile> images,
    @RequestPart(required = false) List<MultipartFile> videos,
    @RequestPart(required = false) List<MultipartFile> files);


  @PostMapping("/videos/_call/incoming")
  ResponseEntity<Object> producerVideoCallIncoming(@RequestParam List<UUID> userIds, @RequestParam(required = false) String message);
}
