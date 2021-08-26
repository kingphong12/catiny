package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.MessageGroupAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.MessageGroupAdvanceSearch;
import com.regitiny.catiny.advance.service.MessageGroupAdvanceService;
import com.regitiny.catiny.advance.service.mapper.MessageGroupAdvanceMapper;
import com.regitiny.catiny.common.utils.StringPool;
import com.regitiny.catiny.domain.MessageGroup;
import com.regitiny.catiny.service.MessageGroupQueryService;
import com.regitiny.catiny.service.MessageGroupService;
import com.regitiny.catiny.service.dto.MessageGroupDTO;
import com.regitiny.catiny.util.MasterUserUtil;
import io.vavr.collection.List;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class MessageGroupAdvanceServiceImpl extends AdvanceService<MessageGroup, MessageGroupService, MessageGroupQueryService, MessageGroupAdvanceMapper, MessageGroupAdvanceRepository, MessageGroupAdvanceSearch> implements MessageGroupAdvanceService
{
  private final MessageGroupAdvanceRepository messageGroupAdvanceRepository;

  private final MessageGroupAdvanceSearch messageGroupAdvanceSearch;

  private final MessageGroupAdvanceMapper messageGroupAdvanceMapper;

  private final PermissionAdvanceServiceImpl permissionAdvanceService;

  @Override
  public Page<MessageGroupDTO> getAllMessageGroupsJoined(Pageable pageable)
  {

    return MasterUserUtil.getCurrentMasterUser()
      .map(masterUser -> messageGroupAdvanceRepository.findAllByInfoPermissionsOwner(masterUser, pageable)
        .map(messageGroupAdvanceMapper::e2d))
      .getOrElse(Page.empty());
  }

  @Override
  public Option<MessageGroupDTO> createMessageGroupAndAddUser(List<UUID> userIds, String desiredName)
  {
    log.debug(
      "Request create message group . desiredName : {} , userIds.size() : {} ",
      desiredName,
      userIds.size()
    );
    var currentUser = MasterUserUtil.getCurrentMasterUser().get();
    log.debug(currentUser);
    userIds = userIds.filter(userId -> !userId.equals(currentUser.getUuid())).toList();

    UUID groupId;
    if (userIds.size() == 1 && !currentUser.getUuid().equals(userIds.get(0)))
      groupId = MasterUserUtil.getMasterUserByUuid(userIds.get(0))
        .map(masterUser ->
        {
          var sumLoginSorted = (masterUser.getUuid().compareTo(currentUser.getUuid()) < 0)
            ? masterUser.getUuid() + StringPool.SPACE + currentUser.getUuid()
            : currentUser.getUuid() + StringPool.SPACE + masterUser.getUuid();
          return UUID.nameUUIDFromBytes(DigestUtils.md5Hex(sumLoginSorted).getBytes());
        })
        .getOrNull();
    else if (userIds.size() == 1 && currentUser.getUuid().equals(userIds.get(0)))
      groupId = currentUser.getUuid();
    else
      groupId = UUID.randomUUID();

    var messageGroupExists = messageGroupAdvanceRepository.findOneByUuid(groupId)
      .peek(messageGroup1 -> log.info("this group actually existed . -> uuid = {} ", messageGroup1.getUuid()))
      .getOrNull();
    var messageGroup = Objects.nonNull(messageGroupExists) ?
      messageGroupExists :
      messageGroupAdvanceRepository.save(new MessageGroup().uuid(groupId).groupName(desiredName));
    userIds.map(MasterUserUtil::getMasterUserByUuid)
      .filter(masterUsers -> !masterUsers.isEmpty())
      .filter(masterUsers -> messageGroupAdvanceRepository.findByUuidAndInfoPermissionsOwner(groupId, masterUsers.get()).isEmpty())
      .forEach(masterUser -> permissionAdvanceService.addUserReadOnly(messageGroup.getInfo(), masterUser.get()));
    return Option.of(messageGroupAdvanceMapper.e2d(messageGroup));
  }

//  @Override
//  @Transactional
//  public java.util.List<MessageGroupDTO> addUserToGroup(java.util.List<Long> userIds, final String groupId)
//  {
//    log.debug("Request create message group . groupId : {} , userIds.size() : {} ", groupId, userIds.size());
//
//    var thisUser = UserUtils.thisUser();
//    if (Objects.isNull(thisUser))
//    {
//      log.debug("user not exists");
//      return new ArrayList<>();
//    }
//    if (userIds.isEmpty() || (userIds.size() == 1 && userIds.get(0).equals(thisUser.getId())))
//      return new ArrayList<>();
//    var resultList = new ArrayList<MessageGroupDTO>();
//    messageGroupRepository.findByGroupIdAndUserId(groupId, thisUser.getId())
//      .ifPresentOrElse(messageGroupThisUser ->
//      {
//        var userIdsSet = new HashSet<>(userIds);
//        userIdsSet.stream()
//          .filter(userId -> !userId.equals(thisUser.getId()))
//          .filter(userId -> userRepository.findById(userId).isPresent())
//          .map(userId ->
//          {
//            var messageGroup = new MessageGroup();
//            EntityDefaultPropertiesServiceUtils.setPropertiesBeforeCreate(messageGroup);
//            messageGroup.groupId(messageGroupThisUser.getGroupId())
//              .addBy(thisUser.getLogin())
//              .userId(userId)
//              .groupName(messageGroupThisUser.getGroupName())
//              .lastContent(messageGroupThisUser.getLastContent());
//            return messageGroupRepository.save(messageGroup);
//          })
//          .map(messageGroupSearchRepository::save)
//          .map(messageGroupMapper::toDto)
//          .forEach(resultList::add);
//      }, () ->
//      {
//        log.debug("group not exists or you not in this group");
//      });
//    return resultList;
//  }


}
