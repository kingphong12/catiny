package com.regitiny.catiny.advance.service;

import com.regitiny.catiny.advance.repository.FriendAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.FriendAdvanceSearch;
import com.regitiny.catiny.advance.service.mapper.FriendAdvanceMapper;
import com.regitiny.catiny.domain.Friend;
import com.regitiny.catiny.service.FriendQueryService;
import com.regitiny.catiny.service.FriendService;

/**
 * AdvanceService layer for {@link Friend}.
 *
 * @see FriendService is base service generate by jhipster
 */
public interface FriendAdvanceService extends BaseSrvice<Friend, FriendService, FriendQueryService, FriendAdvanceMapper, FriendAdvanceRepository, FriendAdvanceSearch>
{
}
