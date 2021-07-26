package com.regitiny.catiny.advance.service;

import com.regitiny.catiny.advance.repository.RankUserAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.RankUserAdvanceSearch;
import com.regitiny.catiny.advance.service.mapper.RankUserAdvanceMapper;
import com.regitiny.catiny.domain.RankUser;
import com.regitiny.catiny.service.RankUserQueryService;
import com.regitiny.catiny.service.RankUserService;

/**
 * AdvanceService layer for {@link RankUser}.
 *
 * @see RankUserService is base service generate by jhipster
 */
public interface RankUserAdvanceService extends BaseSrvice<RankUser, RankUserService, RankUserQueryService, RankUserAdvanceMapper, RankUserAdvanceRepository, RankUserAdvanceSearch>
{
}
