package com.regitiny.catiny.advance.service;

import com.regitiny.catiny.advance.repository.GroupPostAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.GroupPostAdvanceSearch;
import com.regitiny.catiny.advance.service.mapper.GroupPostAdvanceMapper;
import com.regitiny.catiny.domain.GroupPost;
import com.regitiny.catiny.service.GroupPostQueryService;
import com.regitiny.catiny.service.GroupPostService;

/**
 * AdvanceService layer for {@link GroupPost}.
 *
 * @see GroupPostService is base service generate by jhipster
 */
public interface GroupPostAdvanceService extends BaseSrvice<GroupPost, GroupPostService, GroupPostQueryService, GroupPostAdvanceMapper, GroupPostAdvanceRepository, GroupPostAdvanceSearch>
{
}
