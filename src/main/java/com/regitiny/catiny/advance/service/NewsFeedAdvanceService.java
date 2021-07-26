package com.regitiny.catiny.advance.service;

import com.regitiny.catiny.advance.repository.NewsFeedAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.NewsFeedAdvanceSearch;
import com.regitiny.catiny.advance.service.mapper.NewsFeedAdvanceMapper;
import com.regitiny.catiny.domain.NewsFeed;
import com.regitiny.catiny.service.NewsFeedQueryService;
import com.regitiny.catiny.service.NewsFeedService;

/**
 * AdvanceService layer for {@link NewsFeed}.
 *
 * @see NewsFeedService is base service generate by jhipster
 */
public interface NewsFeedAdvanceService extends BaseSrvice<NewsFeed, NewsFeedService, NewsFeedQueryService, NewsFeedAdvanceMapper, NewsFeedAdvanceRepository, NewsFeedAdvanceSearch>
{
}
