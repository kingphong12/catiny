package com.regitiny.catiny.advance.controller.impl;

import com.regitiny.catiny.advance.controller.rest.MessageManagement;
import com.regitiny.catiny.advance.service.MessageContentAdvanceService;
import com.regitiny.catiny.advance.service.MessageGroupAdvanceService;
import com.regitiny.catiny.service.dto.MessageContentDTO;
import com.regitiny.catiny.service.dto.MessageGroupDTO;
import com.regitiny.catiny.util.MasterUserUtils;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.PaginationUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.regitiny.catiny.util.WebSocketUtils.topicConsumer;

@Log4j2
@RestController
@RequiredArgsConstructor
public class MessageManagementImpl implements MessageManagement
{
  private final SimpMessageSendingOperations messagingTemplate;
  private final MessageGroupAdvanceService messageGroupAdvanceService;
  private final MessageContentAdvanceService messageContentAdvanceService;
  private final SimpMessagingTemplate simpMessagingTemplate;

  @Override
  public ResponseEntity<List<String>> getAllMessageGroupsJoined(Pageable pageable)
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
//    9 8 7 ; 6 5 4 ; 3 2 1 ->  7 8 9 ; 4 5 6 ; 3 2 1
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
        messageGroupAdvanceService.getMasterUserDetailsPublicByMessageGroupId(messageContentDTO.getGroup().getUuid()).forEach(masterUserDTO ->
          messagingTemplate.convertAndSendToUser(masterUserDTO.getUuid().toString(), topicConsumer("/messages/groups/" + messageGroupId), messageContentDTO)))
      .map(messageContentDTO -> ResponseEntity.status(HttpStatus.CREATED).body(messageContentDTO))
      .getOrElse(ResponseEntity.status(HttpStatus.FORBIDDEN).build());
  }

  @Override
  public ResponseEntity<Object> producerVideoCallIncoming(List<UUID> userIds, String message)
  {
    var result = new JSONObject();
    var masterUsers = userIds.stream()
      .map(MasterUserUtils::getMasterUserByUuid)
      .map(Option::get)
      .collect(Collectors.toList());
    result.put("videoGroupId", UUID.randomUUID())
      .put("masterUsers", new JSONArray(masterUsers));
    MasterUserUtils.getCurrentMasterUserDTO().forEach(masterUserDTO -> result.put("fromUser", new JSONObject(masterUserDTO)));

    simpMessagingTemplate.convertAndSendToUser("00000000-0000-0000-0000-000000000001", message, "hihi");
    simpMessagingTemplate.convertAndSend(message, "hihi");
    simpMessagingTemplate.convertAndSendToUser("admin", "/topic/users/00000000-0000-0000-0000-000000000001/test", "hihi");
    return null;
  }

}
