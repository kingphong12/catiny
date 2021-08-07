package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.BaseInfoAdvanceRepository;
import com.regitiny.catiny.advance.repository.HistoryUpdateAdvanceRepository;
import com.regitiny.catiny.advance.repository.MasterUserAdvanceRepository;
import com.regitiny.catiny.advance.repository.PermissionAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.MasterUserAdvanceSearch;
import com.regitiny.catiny.advance.service.MasterUserAdvanceService;
import com.regitiny.catiny.advance.service.mapper.MasterUserAdvanceMapper;
import com.regitiny.catiny.domain.BaseInfo;
import com.regitiny.catiny.domain.HistoryUpdate;
import com.regitiny.catiny.domain.MasterUser;
import com.regitiny.catiny.domain.Permission;
import com.regitiny.catiny.domain.enumeration.ProcessStatus;
import com.regitiny.catiny.security.SecurityUtils;
import com.regitiny.catiny.service.MasterUserQueryService;
import com.regitiny.catiny.service.MasterUserService;
import com.regitiny.catiny.service.UserService;
import com.regitiny.catiny.tools.utils.StringPool;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Objects;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class MasterUserAdvanceServiceImpl extends AdvanceService<MasterUser, MasterUserService, MasterUserQueryService, MasterUserAdvanceMapper, MasterUserAdvanceRepository, MasterUserAdvanceSearch> implements MasterUserAdvanceService
{
  private final MasterUserAdvanceRepository masterUserAdvanceRepository;
  private final MasterUserAdvanceSearch masterUserAdvanceSearch;
  private final MasterUserAdvanceMapper masterUserAdvanceMapper;
  private final UserService userService;

  private final BaseInfoAdvanceRepository baseInfoAdvanceRepository;
  private final PermissionAdvanceRepository permissionAdvanceRepository;
  private final HistoryUpdateAdvanceRepository historyUpdateAdvanceRepository;


  public Option<MasterUser> getCurrentMasterUser()
  {
    return SecurityUtils.getCurrentUserLogin()
      .map(masterUserAdvanceRepository::findOneByUserLogin)
      .orElseGet(() ->
      {
        log.warn("current user login not exists");
        return Option.none();
      });
  }

  public Option<MasterUser> getAnonymousMasterUser()
  {
    return masterUserAdvanceRepository.findById(100L)
      .map(Option::of)
      .orElseGet(() ->
      {
        log.warn("anonymous user not exists");
        return Option.none();
      });
  }

  @Override
//  @Transactional(propagation = Propagation.NESTED)
  public Option<MasterUser> createMasterUser(String login)
  {
    return userService.getOneByLogin(login)
      .map(user -> masterUserAdvanceRepository.findOneByUserLogin(user.getLogin())
        .map(masterUser ->
        {
          log.warn("MasterUeser really exist! masterUser :{}", masterUser);
          return Option.of(masterUser);
        })
        .getOrElse(() ->
        {
          var now = Instant.now();
          var permission = permissionAdvanceRepository
            .save(new Permission()
              .read(true)
              .write(true)
              .add(true)
              .share(true)
              .delete(true)
              .level(0));

          var historyUpdate = historyUpdateAdvanceRepository
            .save(new HistoryUpdate()
              .version(0)
              .content(null));

          var baseInfo = baseInfoAdvanceRepository
            .save(new BaseInfo()
              .processStatus(ProcessStatus.NOT_PROCESSED)
              .createdDate(now)
              .modifiedDate(now)
              .addPermission(permission)
              .priorityIndex(0L)
              .countUse(0L)
              .addHistory(historyUpdate));

          var masterUser = masterUserAdvanceRepository
            .save(new MasterUser()
              .fullName(Option.when(Objects.nonNull(user.getLastName()) && Objects.nonNull(user.getFirstName()),
                user.getLastName() + StringPool.SPACE + user.getFirstName()).getOrElse("No Name"))
              .user(user));
//              .addMyPermission(permission));

          masterUser = masterUserAdvanceRepository.save(masterUser.addPermission(permission));

          baseInfoAdvanceRepository.save(baseInfo
            .owner(masterUser)
            .createdBy(masterUser)
            .modifiedBy(masterUser));

          return Option.of(masterUser);
        }))
      .getOrElse(() ->
      {
        log.warn("user not exist");
        return Option.none();
      });
  }
}
