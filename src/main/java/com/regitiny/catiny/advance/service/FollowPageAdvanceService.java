package com.regitiny.catiny.advance.service;

import com.regitiny.catiny.advance.repository.FollowPageAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.FollowPageAdvanceSearch;
import com.regitiny.catiny.advance.service.mapper.FollowPageAdvanceMapper;
import com.regitiny.catiny.domain.FollowPage;
import com.regitiny.catiny.service.FollowPageQueryService;
import com.regitiny.catiny.service.FollowPageService;

/**
 * AdvanceService layer for {@link FollowPage}.
 *
 * @see FollowPageService is base service generate by jhipster
 */
public interface FollowPageAdvanceService extends BaseSrvice<FollowPage, FollowPageService, FollowPageQueryService, FollowPageAdvanceMapper, FollowPageAdvanceRepository, FollowPageAdvanceSearch>
{
}
