package com.regitiny.catiny.advance.service;

import com.regitiny.catiny.advance.repository.BaseInfoAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.BaseInfoAdvanceSearch;
import com.regitiny.catiny.advance.service.mapper.BaseInfoAdvanceMapper;
import com.regitiny.catiny.domain.BaseInfo;
import com.regitiny.catiny.service.BaseInfoQueryService;
import com.regitiny.catiny.service.BaseInfoService;

/**
 * AdvanceService layer for {@link BaseInfo}.
 *
 * @see BaseInfoService is base service generate by jhipster
 */
public interface BaseInfoAdvanceService extends BaseSrvice<BaseInfo, BaseInfoService, BaseInfoQueryService, BaseInfoAdvanceMapper, BaseInfoAdvanceRepository, BaseInfoAdvanceSearch>
{
}
