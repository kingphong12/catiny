package com.regitiny.catiny.advance.service;

import com.regitiny.catiny.advance.repository.UserProfileAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.UserProfileAdvanceSearch;
import com.regitiny.catiny.advance.service.mapper.UserProfileAdvanceMapper;
import com.regitiny.catiny.domain.UserProfile;
import com.regitiny.catiny.service.UserProfileQueryService;
import com.regitiny.catiny.service.UserProfileService;

/**
 * AdvanceService layer for {@link UserProfile}.
 *
 * @see UserProfileService is base service generate by jhipster
 */
public interface UserProfileAdvanceService extends BaseSrvice<UserProfile, UserProfileService, UserProfileQueryService, UserProfileAdvanceMapper, UserProfileAdvanceRepository, UserProfileAdvanceSearch>
{
}
