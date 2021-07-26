package com.regitiny.catiny.advance.service;

import com.regitiny.catiny.advance.repository.PagePostAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.PagePostAdvanceSearch;
import com.regitiny.catiny.advance.service.mapper.PagePostAdvanceMapper;
import com.regitiny.catiny.domain.PagePost;
import com.regitiny.catiny.service.PagePostQueryService;
import com.regitiny.catiny.service.PagePostService;

/**
 * AdvanceService layer for {@link PagePost}.
 *
 * @see PagePostService is base service generate by jhipster
 */
public interface PagePostAdvanceService extends BaseSrvice<PagePost, PagePostService, PagePostQueryService, PagePostAdvanceMapper, PagePostAdvanceRepository, PagePostAdvanceSearch>
{
}
