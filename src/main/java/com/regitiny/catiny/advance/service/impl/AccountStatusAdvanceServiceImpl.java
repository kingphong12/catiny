package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.AccountStatusAdvanceRepository;
import com.regitiny.catiny.advance.repository.DeviceStatusAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.AccountStatusAdvanceSearch;
import com.regitiny.catiny.advance.service.AccountStatusAdvanceService;
import com.regitiny.catiny.advance.service.mapper.AccountStatusAdvanceMapper;
import com.regitiny.catiny.domain.AccountStatus;
import com.regitiny.catiny.domain.DeviceStatus;
import com.regitiny.catiny.domain.MasterUser;
import com.regitiny.catiny.domain.enumeration.DeviceType;
import com.regitiny.catiny.domain.enumeration.StatusName;
import com.regitiny.catiny.security.SecurityUtils;
import com.regitiny.catiny.service.AccountStatusQueryService;
import com.regitiny.catiny.service.AccountStatusService;
import com.regitiny.catiny.util.MasterUserUtils;
import io.vavr.collection.List;
import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Objects;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class AccountStatusAdvanceServiceImpl extends AdvanceService<AccountStatus, AccountStatusService, AccountStatusQueryService, AccountStatusAdvanceMapper, AccountStatusAdvanceRepository, AccountStatusAdvanceSearch> implements AccountStatusAdvanceService
{
  private final AccountStatusAdvanceRepository accountStatusAdvanceRepository;

  private final AccountStatusAdvanceSearch accountStatusAdvanceSearch;

  private final AccountStatusAdvanceMapper accountStatusAdvanceMapper;

  private final DeviceStatusAdvanceRepository deviceStatusAdvanceRepository;
  @Autowired
  private DeviceStatusAdvanceServiceImpl deviceStatusAdvanceService;


  @Override
  public void updateStatusCurrently(Device device)
  {

  }

  @Override
  public boolean checkIsOnline(MasterUser masterUser)
  {
    return accountStatusAdvanceRepository.findOneByInfoOwnerAndAccountStatus(masterUser, StatusName.ONLINE).isDefined();
  }

  @Override
  public void onlineStatus(Device device)
  {
    var sessionKeyInJwt = SecurityUtils.getCurrentSessionKey().get();
    MasterUserUtils.getCurrentMasterUser()
      .flatMap(accountStatusAdvanceRepository::findOneByInfoOwner)
      .orElse(Option.of(new AccountStatus()))
      .map(accountStatus -> accountStatusAdvanceRepository.save(accountStatus
        .lastVisited(Instant.now())
        .accountStatus(StatusName.ONLINE)))
      .forEach(accountStatus ->
        deviceStatusAdvanceService.local().advanceRepository.findAllByAccountStatus(accountStatus)
          .filter(deviceStatusResult ->
            Try.of(() -> new JSONObject(deviceStatusResult.getStatusComment()))
              .orElse(Try.of(JSONObject::new))
              .map(jsonObject ->
                jsonObject.has("sessionKey") && sessionKeyInJwt.equals(jsonObject.getString("sessionKey"))).getOrElse(false))
          .orElse(List.of(new DeviceStatus()
            .deviceStatus(StatusName.ONLINE)
            .lastVisited(Instant.now())
            .accountStatus(accountStatus)))
          .forEach(deviceStatus ->
          {
            if (Objects.nonNull(device))
            {
              if (device.isMobile())
                deviceStatus.setDeviceType(DeviceType.MOBILE);
              else if (device.isTablet())
                deviceStatus.setDeviceType(DeviceType.TABLE);
              else
                deviceStatus.setDeviceType(DeviceType.DESKTOP);
              deviceStatus.deviceName(device.getDevicePlatform().name());
            }
            else if (Objects.isNull(deviceStatus.getId()))
              deviceStatus
                .deviceType(DeviceType.OTHER_DEVICE)
                .deviceName("UNKNOWN");
            deviceStatusAdvanceRepository.save(deviceStatus.statusComment(new JSONObject().put("sessionKey", sessionKeyInJwt).toString()));
          }));
  }

  @Override
  public void offlineStatus()
  {
    MasterUserUtils.getCurrentMasterUser().forEach(masterUser ->
    {
      var sessionKeyInJwt = SecurityUtils.getCurrentSessionKey().get();
      deviceStatusAdvanceRepository.findAllByInfoOwner(masterUser)
        .filter(deviceStatusResult ->
          Try.of(() -> new JSONObject(deviceStatusResult.getStatusComment()))
            .orElse(Try.of(JSONObject::new))
            .map(jsonObject ->
              jsonObject.has("sessionKey") && sessionKeyInJwt.equals(jsonObject.getString("sessionKey"))).getOrElse(false))
        .forEach(deviceStatus -> deviceStatusAdvanceRepository.save(deviceStatus.lastVisited(Instant.now()).deviceStatus(StatusName.OFFLINE)));
      var countDeviceOnline = deviceStatusAdvanceRepository.findAllByInfoOwner(masterUser)
        .count(deviceStatus -> deviceStatus.getDeviceStatus().equals(StatusName.ONLINE));
      accountStatusAdvanceRepository.findOneByInfoOwner(masterUser)
        .forEach(accountStatus ->
        {
          if (countDeviceOnline == 0)
            accountStatus.accountStatus(StatusName.OFFLINE);
          accountStatus.lastVisited(Instant.now());
          accountStatusAdvanceRepository.save(accountStatus);
        });
    });
  }
}
