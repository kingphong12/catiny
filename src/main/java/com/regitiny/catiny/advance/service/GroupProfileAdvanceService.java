package com.regitiny.catiny.advance.service;

import com.regitiny.catiny.advance.repository.GroupProfileAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.GroupProfileAdvanceSearch;
import com.regitiny.catiny.advance.service.mapper.GroupProfileAdvanceMapper;
import com.regitiny.catiny.domain.GroupProfile;
import com.regitiny.catiny.service.GroupProfileQueryService;
import com.regitiny.catiny.service.GroupProfileService;

/**
 * AdvanceService layer for {@link GroupProfile}.
 *
 * @see GroupProfileService is base service generate by jhipster
 */
public interface GroupProfileAdvanceService extends BaseSrvice<GroupProfile, GroupProfileService, GroupProfileQueryService, GroupProfileAdvanceMapper, GroupProfileAdvanceRepository, GroupProfileAdvanceSearch>
{
}
