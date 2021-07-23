package com.regitiny.catiny.advance.service;

import com.regitiny.catiny.advance.repository.RankGroupAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.RankGroupAdvanceSearch;
import com.regitiny.catiny.advance.service.mapper.RankGroupAdvanceMapper;
import com.regitiny.catiny.domain.RankGroup;
import com.regitiny.catiny.service.RankGroupQueryService;
import com.regitiny.catiny.service.RankGroupService;

/**
 * AdvanceService layer for {@link RankGroup}.
 *
 * @see RankGroupService is base service generate by jhipster
 */
public interface RankGroupAdvanceService extends BaseSrvice<RankGroup, RankGroupService, RankGroupQueryService, RankGroupAdvanceMapper, RankGroupAdvanceRepository, RankGroupAdvanceSearch>
{
}
