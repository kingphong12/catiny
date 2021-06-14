package com.regitiny.catiny.advance.controller.rest;

import com.regitiny.catiny.advance.controller.model.MessageGroupModel;
import com.regitiny.catiny.service.dto.MessageGroupDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RequestMapping("/api/o")
public interface MessageGroupManagement
{

  /**
   * {@code POST  /message-groups} : Create a new messageGroup.
   *
   * @param userIds danh sách những user được thêm vào trong lúc tạo group
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new messageGroupDTO, or with status {@code 400 (Bad Request)} if the messageGroup has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/message-groups/new-group")
  ResponseEntity<List<MessageGroupDTO>> createNewMessageGroup(
    @RequestParam("groupName") String groupName,
    @RequestParam("lastContent") String lastContent,
    @RequestParam("userIds") List<Long> userIds
  ) throws URISyntaxException;


  @PostMapping("/message-groups/{groupId}")
  ResponseEntity<List<MessageGroupDTO>> addUserToGroup(@RequestParam List<Long> userIds, @PathVariable String groupId)
    throws URISyntaxException;


  @GetMapping("/message-groups/joined")
  ResponseEntity<List<MessageGroupModel.Response>> getAllGroupsJoined(Pageable pageable) throws URISyntaxException;
}
