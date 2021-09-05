package com.regitiny.catiny.advance.controller.rest.impl;

import com.regitiny.catiny.advance.controller.rest.MessageManagement;
import com.regitiny.catiny.advance.service.MasterUserAdvanceService;
import com.regitiny.catiny.advance.service.MessageContentAdvanceService;
import com.regitiny.catiny.advance.service.MessageGroupAdvanceService;
import com.regitiny.catiny.service.dto.MessageContentDTO;
import com.regitiny.catiny.service.dto.MessageGroupDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.PaginationUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Log4j2
@RestController
@RequiredArgsConstructor
public class MessageManagementImpl implements MessageManagement
{
  private final SimpMessageSendingOperations messagingTemplate;
  private final MessageGroupAdvanceService messageGroupAdvanceService;
  private final MessageContentAdvanceService messageContentAdvanceService;
  private final MasterUserAdvanceService masterUserAdvanceService;

  @Override
  public ResponseEntity<List<MessageGroupDTO>> getAllMessageGroupsJoined(Pageable pageable)
  {
    log.debug("request get all messages group user joined , pageable = {}", pageable);
    var page = messageGroupAdvanceService.getAllMessageGroupsJoined(pageable);
    var headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

  @Override
  public ResponseEntity<List<MessageContentDTO>> getMessageContentInMessageGroup(UUID uuid, Pageable pageable)
  {
    log.debug("request get messages content in message group id ={} , pageable = {}", uuid, pageable);
    var page = messageContentAdvanceService.getContentInGroup(uuid, pageable);
    var headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    var content = new ArrayList<MessageContentDTO>();
    for (int i = page.getContent().size() - 1; i >= 0; i--)
      content.add(page.getContent().get(i));
    return ResponseEntity.ok().headers(headers).body(content);
  }

  @Override
  public ResponseEntity<MessageGroupDTO> createMessageGroup(List<UUID> userIds, String desiredName)
  {
    return messageGroupAdvanceService.createMessageGroupAndAddUser(io.vavr.collection.List.ofAll(userIds), desiredName)
      .map(ResponseEntity::ok)
      .getOrElse(ResponseEntity.badRequest().build());
  }

  @Override
  public ResponseEntity<Object> getMasterUsersDetailsPublicByMessageGroupId(UUID messageGroupId)
  {
    return ResponseEntity.ok(messageGroupAdvanceService.getMasterUserDetailsPublicByMessageGroupId(messageGroupId).toJavaList());
  }

  @Override
  public ResponseEntity<MessageContentDTO> sendContentToGroup(UUID messageGroupId, String content, List<MultipartFile> images, List<MultipartFile> videos, List<MultipartFile> files)
  {
    return messageContentAdvanceService.sendContentToGroup(messageGroupId, content, images, videos, files)
      .peek(messageContentDTO ->
      {
        var topic = "/topic/users/${userId}/messages/new";
        messageGroupAdvanceService.getMasterUserDetailsPublicByMessageGroupId(messageContentDTO.getGroup().getUuid()).forEach(masterUserDTO ->
          messagingTemplate.convertAndSend(topic.replace("${userId}", masterUserDTO.getUuid().toString()), messageContentDTO));
      })
      .map(messageContentDTO -> ResponseEntity.status(HttpStatus.CREATED).body(messageContentDTO))
      .getOrElse(ResponseEntity.status(HttpStatus.FORBIDDEN).build());
  }
}
