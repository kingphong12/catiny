package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.PagePostAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.PagePostAdvanceSearch;
import com.regitiny.catiny.advance.service.PagePostAdvanceService;
import com.regitiny.catiny.advance.service.mapper.PagePostAdvanceMapper;
import com.regitiny.catiny.domain.PagePost;
import com.regitiny.catiny.service.PagePostQueryService;
import com.regitiny.catiny.service.PagePostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class PagePostAdvanceServiceImpl extends AdvanceService<PagePost, PagePostService, PagePostQueryService, PagePostAdvanceMapper, PagePostAdvanceRepository, PagePostAdvanceSearch> implements PagePostAdvanceService
{
  private final PagePostAdvanceRepository pagePostAdvanceRepository;

  private final PagePostAdvanceSearch pagePostAdvanceSearch;

  private final PagePostAdvanceMapper pagePostAdvanceMapper;
}
