package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.NewsFeedAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.NewsFeedAdvanceSearch;
import com.regitiny.catiny.advance.service.NewsFeedAdvanceService;
import com.regitiny.catiny.advance.service.mapper.NewsFeedAdvanceMapper;
import com.regitiny.catiny.domain.NewsFeed;
import com.regitiny.catiny.service.NewsFeedQueryService;
import com.regitiny.catiny.service.NewsFeedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class NewsFeedAdvanceServiceImpl extends AdvanceService<NewsFeed, NewsFeedService, NewsFeedQueryService, NewsFeedAdvanceMapper, NewsFeedAdvanceRepository, NewsFeedAdvanceSearch> implements NewsFeedAdvanceService
{
  private final NewsFeedAdvanceRepository newsFeedAdvanceRepository;

  private final NewsFeedAdvanceSearch newsFeedAdvanceSearch;

  private final NewsFeedAdvanceMapper newsFeedAdvanceMapper;
}
