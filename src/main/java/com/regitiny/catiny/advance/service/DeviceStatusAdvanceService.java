package com.regitiny.catiny.advance.service;

import com.regitiny.catiny.advance.repository.DeviceStatusAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.DeviceStatusAdvanceSearch;
import com.regitiny.catiny.advance.service.mapper.DeviceStatusAdvanceMapper;
import com.regitiny.catiny.domain.DeviceStatus;
import com.regitiny.catiny.service.DeviceStatusQueryService;
import com.regitiny.catiny.service.DeviceStatusService;

/**
 * AdvanceService layer for {@link DeviceStatus}.
 *
 * @see DeviceStatusService is base service generate by jhipster
 */
public interface DeviceStatusAdvanceService extends BaseSrvice<DeviceStatus, DeviceStatusService, DeviceStatusQueryService, DeviceStatusAdvanceMapper, DeviceStatusAdvanceRepository, DeviceStatusAdvanceSearch>
{
}
