package com.regitiny.catiny.advance.service;

import com.regitiny.catiny.advance.repository.FollowUserAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.FollowUserAdvanceSearch;
import com.regitiny.catiny.advance.service.mapper.FollowUserAdvanceMapper;
import com.regitiny.catiny.domain.FollowUser;
import com.regitiny.catiny.service.FollowUserQueryService;
import com.regitiny.catiny.service.FollowUserService;

/**
 * AdvanceService layer for {@link FollowUser}.
 *
 * @see FollowUserService is base service generate by jhipster
 */
public interface FollowUserAdvanceService extends BaseSrvice<FollowUser, FollowUserService, FollowUserQueryService, FollowUserAdvanceMapper, FollowUserAdvanceRepository, FollowUserAdvanceSearch>
{
}
