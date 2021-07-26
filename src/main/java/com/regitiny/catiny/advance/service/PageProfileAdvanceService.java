package com.regitiny.catiny.advance.service;

import com.regitiny.catiny.advance.repository.PageProfileAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.PageProfileAdvanceSearch;
import com.regitiny.catiny.advance.service.mapper.PageProfileAdvanceMapper;
import com.regitiny.catiny.domain.PageProfile;
import com.regitiny.catiny.service.PageProfileQueryService;
import com.regitiny.catiny.service.PageProfileService;

/**
 * AdvanceService layer for {@link PageProfile}.
 *
 * @see PageProfileService is base service generate by jhipster
 */
public interface PageProfileAdvanceService extends BaseSrvice<PageProfile, PageProfileService, PageProfileQueryService, PageProfileAdvanceMapper, PageProfileAdvanceRepository, PageProfileAdvanceSearch>
{
}
