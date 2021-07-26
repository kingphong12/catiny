package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.DeviceStatusAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.DeviceStatusAdvanceSearch;
import com.regitiny.catiny.advance.service.DeviceStatusAdvanceService;
import com.regitiny.catiny.advance.service.mapper.DeviceStatusAdvanceMapper;
import com.regitiny.catiny.domain.DeviceStatus;
import com.regitiny.catiny.service.DeviceStatusQueryService;
import com.regitiny.catiny.service.DeviceStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class DeviceStatusAdvanceServiceImpl extends AdvanceService<DeviceStatus, DeviceStatusService, DeviceStatusQueryService, DeviceStatusAdvanceMapper, DeviceStatusAdvanceRepository, DeviceStatusAdvanceSearch> implements DeviceStatusAdvanceService
{
  private final DeviceStatusAdvanceRepository deviceStatusAdvanceRepository;

  private final DeviceStatusAdvanceSearch deviceStatusAdvanceSearch;

  private final DeviceStatusAdvanceMapper deviceStatusAdvanceMapper;
}
