package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.MessageContentAdvanceRepository;
import com.regitiny.catiny.advance.repository.MessageGroupAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.MessageContentAdvanceSearch;
import com.regitiny.catiny.advance.service.MessageContentAdvanceService;
import com.regitiny.catiny.advance.service.mapper.MessageContentAdvanceMapper;
import com.regitiny.catiny.common.utils.StringUtils;
import com.regitiny.catiny.domain.MessageContent;
import com.regitiny.catiny.domain.Permission;
import com.regitiny.catiny.service.MessageContentQueryService;
import com.regitiny.catiny.service.MessageContentService;
import com.regitiny.catiny.service.dto.MasterUserDTO;
import com.regitiny.catiny.service.dto.MessageContentDTO;
import com.regitiny.catiny.util.MasterUserUtil;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class MessageContentAdvanceServiceImpl extends AdvanceService<MessageContent, MessageContentService, MessageContentQueryService, MessageContentAdvanceMapper, MessageContentAdvanceRepository, MessageContentAdvanceSearch> implements MessageContentAdvanceService
{
  private final MessageContentAdvanceRepository messageContentAdvanceRepository;

  private final MessageContentAdvanceSearch messageContentAdvanceSearch;

  private final MessageContentAdvanceMapper messageContentAdvanceMapper;

  private final MessageGroupAdvanceRepository messageGroupAdvanceRepository;

  @Autowired
  private PermissionAdvanceServiceImpl permissionAdvanceService;

  @Override
  public Page<MessageContentDTO> getContentInGroup(UUID uuid, Pageable pageable)
  {
    return MasterUserUtil.getCurrentMasterUser()
      .map(masterUser -> messageContentAdvanceRepository.findAllByGroupUuidOrderByIdDesc(uuid, pageable)
        .map(messageContent ->
        {
          var dto = messageContentAdvanceMapper.e2d(messageContent);
          var ownerDTO = new MasterUserDTO();
          ownerDTO.setUuid(messageContent.getInfo().getOwner().getUuid());
          dto.getInfo().setOwner(ownerDTO);
          return dto;
        }))
      .peek(messageContentDTOS -> messageContentDTOS.stream().sorted(Comparator.comparingLong(MessageContentDTO::getId)))
      .getOrElse(Page.empty());
  }

  @Override
  public Option<MessageContentDTO> sendContentToGroup(UUID messageGroupId, String content, List<MultipartFile> images, List<MultipartFile> videos, List<MultipartFile> files)
  {
    return messageGroupAdvanceRepository.findOneByUuid(messageGroupId)
      .map(messageGroup ->
      {
        var messageContent = messageContentAdvanceRepository.save(new MessageContent()
          .group(messageGroup)
          .content(content)
          .searchField(StringUtils.cleanCharVI(content)).status("SENT"));
        messageGroup.getInfo().getPermissions().stream()
          .filter(Permission::getRead)
          .map(Permission::getOwner)
          .filter(masterUser -> masterUser != MasterUserUtil.getCurrentMasterUser().get())
          .forEach(masterUser ->
            permissionAdvanceService.addUserReadOnly(messageContent.getInfo(), masterUser));
        return messageContent;
      })
      .map(messageContent ->
      {
        var dto = messageContentAdvanceMapper.e2d(messageContent);
        var owner = new MasterUserDTO();
        owner.setUuid(messageContent.getInfo().getOwner().getUuid());
        dto.getInfo().setOwner(owner);
        return dto;
      });
  }
}
