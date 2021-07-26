package com.regitiny.catiny.advance.service;

import com.regitiny.catiny.advance.repository.FollowGroupAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.FollowGroupAdvanceSearch;
import com.regitiny.catiny.advance.service.mapper.FollowGroupAdvanceMapper;
import com.regitiny.catiny.domain.FollowGroup;
import com.regitiny.catiny.service.FollowGroupQueryService;
import com.regitiny.catiny.service.FollowGroupService;

/**
 * AdvanceService layer for {@link FollowGroup}.
 *
 * @see FollowGroupService is base service generate by jhipster
 */
public interface FollowGroupAdvanceService extends BaseSrvice<FollowGroup, FollowGroupService, FollowGroupQueryService, FollowGroupAdvanceMapper, FollowGroupAdvanceRepository, FollowGroupAdvanceSearch>
{
}
