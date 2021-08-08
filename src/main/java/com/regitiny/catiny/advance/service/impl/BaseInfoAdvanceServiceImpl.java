package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.BaseInfoAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.BaseInfoAdvanceSearch;
import com.regitiny.catiny.advance.service.BaseInfoAdvanceService;
import com.regitiny.catiny.advance.service.mapper.BaseInfoAdvanceMapper;
import com.regitiny.catiny.domain.BaseInfo;
import com.regitiny.catiny.domain.MasterUser;
import com.regitiny.catiny.domain.enumeration.ProcessStatus;
import com.regitiny.catiny.service.BaseInfoQueryService;
import com.regitiny.catiny.service.BaseInfoService;
import com.regitiny.catiny.util.MasterUserUtil;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class BaseInfoAdvanceServiceImpl extends AdvanceService<BaseInfo, BaseInfoService, BaseInfoQueryService, BaseInfoAdvanceMapper, BaseInfoAdvanceRepository, BaseInfoAdvanceSearch> implements BaseInfoAdvanceService
{
  private final BaseInfoAdvanceRepository baseInfoAdvanceRepository;
  private final BaseInfoAdvanceSearch baseInfoAdvanceSearch;
  private final BaseInfoAdvanceMapper baseInfoAdvanceMapper;
  private final PermissionAdvanceServiceImpl permissionAdvanceService;
  @Lazy
  @Autowired
  private HistoryUpdateAdvanceServiceImpl historyUpdateAdvanceService;


  public Option<BaseInfo> findByUuid(UUID uuid)
  {
    return baseInfoAdvanceRepository.findOneByUuid(uuid)
      .map(Option::of)
      .getOrElse(() ->
      {
        log.info("not found BaseInfo by id = {}", uuid);
        return Option.none();
      });
  }

  public Option<BaseInfo> findById(Long id)
  {
    return baseInfoAdvanceRepository.findById(id)
      .map(Option::of)
      .orElseGet(() ->
      {
        log.info("not found BaseInfo by id = {}", id);
        return Option.none();
      });
  }

  public Option<BaseInfo> findOne(BaseInfo baseInfo)
  {
    return Option
      .when(Objects.nonNull(baseInfo),
        findById(baseInfo.getId())
          .orElse(findByUuid(baseInfo.getUuid()))
          .onEmpty(() -> log.warn("not found this BaseInfo : {}", baseInfo)))
      .onEmpty(() -> log.warn("BaseInfo input is empty")).get();
  }

  public BaseInfo createForOwner()
  {
    var now = Instant.now();

    var baseInfo = baseInfoAdvanceRepository.save(new BaseInfo().uuid(UUID.randomUUID())
      .processStatus(ProcessStatus.NOT_PROCESSED)
//      .modifiedClass(null)
      .createdDate(now)
      .modifiedDate(now)
      .priorityIndex(0L)
      .countUse(0L));

    MasterUserUtil.getCurrentMasterUser()
      .forEach(masterUser -> baseInfo.owner(masterUser)
        .addPermission(permissionAdvanceService.createForOwner())
        .createdBy(masterUser)
        .modifiedBy(masterUser));
    MasterUserUtil.anonymousMasterUser()
      .map(masterUser -> permissionAdvanceService.createForAnonymous())
      .forEach(baseInfo::addPermission);


    return baseInfoAdvanceRepository.save(baseInfo.addHistory(historyUpdateAdvanceService.createFirstVersion(baseInfo)));
  }

  public BaseInfo createWhenCreateMasterUser(MasterUser masterUser)
  {
    var now = Instant.now();
    var ownerPermission = permissionAdvanceService.createForOwner().owner(masterUser);

    var baseInfo = new BaseInfo()
      .processStatus(ProcessStatus.NOT_PROCESSED)
//      .modifiedClass(null)
      .createdDate(now)
      .modifiedDate(now)
      .owner(masterUser)
      .addPermission(ownerPermission)
      .createdBy(masterUser)
      .modifiedBy(masterUser)
      .priorityIndex(0L)
      .countUse(0L);
    baseInfo = baseInfoAdvanceRepository.save(baseInfo);

    var historyUpdate = historyUpdateAdvanceService.createFirstVersion(baseInfo);
    return baseInfo;
  }


}
