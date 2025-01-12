package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.AccountStatusAdvanceRepository;
import com.regitiny.catiny.advance.repository.DeviceStatusAdvanceRepository;
import com.regitiny.catiny.advance.repository.MasterUserAdvanceRepository;
import com.regitiny.catiny.advance.repository.MessageGroupAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.AccountStatusAdvanceSearch;
import com.regitiny.catiny.advance.repository.search.MessageGroupAdvanceSearch;
import com.regitiny.catiny.advance.service.MessageGroupAdvanceService;
import com.regitiny.catiny.advance.service.mapper.AccountStatusAdvanceMapper;
import com.regitiny.catiny.advance.service.mapper.MessageGroupAdvanceMapper;
import com.regitiny.catiny.common.utils.StringPool;
import com.regitiny.catiny.domain.MasterUser;
import com.regitiny.catiny.domain.MessageGroup;
import com.regitiny.catiny.domain.Permission;
import com.regitiny.catiny.domain.User;
import com.regitiny.catiny.domain.enumeration.StatusName;
import com.regitiny.catiny.service.MessageGroupQueryService;
import com.regitiny.catiny.service.MessageGroupService;
import com.regitiny.catiny.service.dto.MasterUserDTO;
import com.regitiny.catiny.service.dto.MessageGroupDTO;
import com.regitiny.catiny.util.MasterUserUtils;
import com.regitiny.catiny.util.WebSocketUtils;
import io.vavr.collection.List;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class MessageGroupAdvanceServiceImpl extends AdvanceService<MessageGroup, MessageGroupService, MessageGroupQueryService, MessageGroupAdvanceMapper, MessageGroupAdvanceRepository, MessageGroupAdvanceSearch> implements MessageGroupAdvanceService
{
  private final MessageGroupAdvanceRepository messageGroupAdvanceRepository;
  private final AccountStatusAdvanceRepository accountStatusAdvanceRepository;
  private final DeviceStatusAdvanceRepository deviceStatusAdvanceRepository;
  private final MasterUserAdvanceRepository masterUserAdvanceRepository;

  private final AccountStatusAdvanceSearch accountStatusAdvanceSearch;
  private final AccountStatusAdvanceMapper accountStatusAdvanceMapper;

  private final SimpMessagingTemplate simpMessagingTemplate;

  private final MessageGroupAdvanceSearch messageGroupAdvanceSearch;

  private final MessageGroupAdvanceMapper messageGroupAdvanceMapper;

  private final PermissionAdvanceServiceImpl permissionAdvanceService;
  private final MasterUserAdvanceServiceImpl masterUserAdvanceService;


  @Override
  public Page<String> getAllMessageGroupsJoined(Pageable pageable)
  {
    return MasterUserUtils.getCurrentMasterUser()
      .map(masterUser -> messageGroupAdvanceRepository.findAllByInfoPermissionsOwner(masterUser, pageable)
        .map(messageGroup ->
        {
          var owner = messageGroup.getInfo().getPermissions().stream().map(Permission::getOwner).collect(Collectors.toList());
          if (owner.size() == 2)
            owner.stream().filter(muTemp -> muTemp.equals(masterUser)).forEach(muTemp -> messageGroup.groupName(muTemp.getFullName()));
          return messageGroup;
        })
        .map(messageGroupAdvanceMapper::e2d)
        .map(messageGroupDTO ->
        {
          var userOnlineInGroup = masterUserAdvanceRepository.findAllByMessageGroupUuid(messageGroupDTO.getUuid())
            .filter(mu -> accountStatusAdvanceRepository.findOneByInfoOwnerAndAccountStatus(mu, StatusName.ONLINE)
              .map(accountStatus -> true)
              .getOrElse(false))
            .map(MasterUser::getUuid).toList();

          return new JSONObject(messageGroupDTO)
            .put("idUserOnline", userOnlineInGroup.asJava())
            .toString();
        }))
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
    var currentUser = MasterUserUtils.getCurrentMasterUser().get();
    log.debug(currentUser);
    userIds = userIds.filter(userId -> !userId.equals(currentUser.getUuid())).toList();

    UUID groupId;
    String avatar;
    if (userIds.size() == 1 && !currentUser.getUuid().equals(userIds.get(0)))
    {
      var thatUser = MasterUserUtils.getMasterUserByUuid(userIds.get(0));
      groupId = thatUser.map(masterUser ->
        {
          var sumLoginSorted = (masterUser.getUuid().compareTo(currentUser.getUuid()) < 0)
            ? masterUser.getUuid() + StringPool.SPACE + currentUser.getUuid()
            : currentUser.getUuid() + StringPool.SPACE + masterUser.getUuid();
          return UUID.nameUUIDFromBytes(DigestUtils.md5Hex(sumLoginSorted).getBytes());
        })
        .getOrNull();
      desiredName = null;
      avatar = null;
    }
    else if (userIds.size() == 1 && currentUser.getUuid().equals(userIds.get(0)))
    {
      groupId = currentUser.getUuid();
      desiredName = currentUser.getFullName();
      avatar = currentUser.getAvatar();
    }
    else if (userIds.size() > 1)
    {
      var avatarLinks = new JSONArray();
      var avatarUrls = new JSONArray();
      if (Objects.isNull(desiredName))
      {
        var sb = new StringBuilder("Group: ");
        List.of(currentUser.getUuid(), userIds.get(0), userIds.get(1))
          .flatMap(MasterUserUtils::getMasterUserByUuid)
          .map(masterUser ->
          {
            var avatarUserJson = new JSONObject(masterUser.getAvatar());
            if (avatarUserJson.has("link") && Objects.nonNull(avatarUserJson.getString("link")))
              avatarLinks.put(avatarUserJson.getString("link"));
            if (avatarUserJson.has("url") && Objects.nonNull(avatarUserJson.getString("url")))
              avatarUrls.put(avatarUserJson.getString("url"));
            return masterUser;
          })
          .map(MasterUser::getUser)
          .map(User::getFirstName)
          .map(s -> sb.append(StringPool.COMMA).append(s));
        desiredName = sb.toString();
      }
      if (avatarLinks.length() < 3 && avatarUrls.length() < 3)
        log.info(" image links and urls are all less 3 . \n-> links :{} \n-> urls :{}", avatarLinks, avatarUrls);
      avatar = new JSONObject().put("links", avatarLinks).put("urls", avatarUrls).toString();
      groupId = UUID.randomUUID();
    }
    else
    {
      log.warn("error unknown");
      groupId = UUID.randomUUID();
      desiredName = groupId.toString();
      avatar = null;
    }


    var messageGroupExists = messageGroupAdvanceRepository.findOneByUuid(groupId)
      .peek(messageGroup1 -> log.info("this group actually existed . -> uuid = {} ", messageGroup1.getUuid()))
      .getOrNull();
    var messageGroup = Objects.nonNull(messageGroupExists) ?
      messageGroupExists :
      messageGroupAdvanceRepository.save(new MessageGroup()
        .uuid(groupId)
        .groupName(desiredName)
        .avatar(avatar));
    userIds.map(MasterUserUtils::getMasterUserByUuid)
      .filter(masterUsers -> !masterUsers.isEmpty())
      .filter(masterUsers -> messageGroupAdvanceRepository.findByUuidAndInfoPermissionsOwner(groupId, masterUsers.get()).isEmpty())
      .forEach(masterUser -> permissionAdvanceService.addUserReadOnly(messageGroup.getInfo(), masterUser.get()));
    return Option.of(messageGroupAdvanceMapper.e2d(messageGroup));
  }

  @Override
  public List<MasterUserDTO> getMasterUserDetailsPublicByMessageGroupId(UUID messageGroupId)
  {
    var masterUserLocalService = masterUserAdvanceService.publicLocal();
    var queryResult = masterUserLocalService.advanceRepository.findAllByMessageGroupUuid(messageGroupId)
      .map(masterUserLocalService.advanceMapper::e2d);
    if (queryResult.size() == 2)
      return queryResult.filter(masterUserDTO -> MasterUserUtils.getCurrentMasterUser()
          .map(currentUser -> masterUserDTO.getUuid().equals(currentUser.getUuid())).getOrElse(false))
        .toList();
    return queryResult;
  }

  @Override
  public void refreshMessageGroupStatus()
  {
    messageGroupAdvanceRepository.findAllByInfoPermissionsOwner(MasterUserUtils.getCurrentMasterUser().get(), PageRequest.ofSize(Integer.MAX_VALUE))
      .stream()
      .forEach(messageGroup ->
      {
        var userOnlineInGroup = masterUserAdvanceRepository.findAllByMessageGroupUuid(messageGroup.getUuid())
          .filter(masterUser -> accountStatusAdvanceRepository.findOneByInfoOwnerAndAccountStatus(masterUser, StatusName.ONLINE)
            .map(accountStatus -> true)
            .getOrElse(false))
          .map(MasterUser::getUuid).toList();

        userOnlineInGroup.toJavaParallelStream().forEach(masterUserUuid ->
          simpMessagingTemplate.convertAndSendToUser(masterUserUuid.toString(), WebSocketUtils.topicConsumer("/notifications/message-group.status"), new JSONObject()
            .put("idUserOnline", userOnlineInGroup.asJava())
            .put("messageGroupId", messageGroup.getUuid())
            .put("refreshTime", Instant.now())
            .toString()));
      });
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
