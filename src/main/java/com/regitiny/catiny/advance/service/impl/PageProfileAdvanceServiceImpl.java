package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.PageProfileAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.PageProfileAdvanceSearch;
import com.regitiny.catiny.advance.service.PageProfileAdvanceService;
import com.regitiny.catiny.advance.service.mapper.PageProfileAdvanceMapper;
import com.regitiny.catiny.domain.PageProfile;
import com.regitiny.catiny.service.PageProfileQueryService;
import com.regitiny.catiny.service.PageProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class PageProfileAdvanceServiceImpl extends AdvanceService<PageProfile, PageProfileService, PageProfileQueryService, PageProfileAdvanceMapper, PageProfileAdvanceRepository, PageProfileAdvanceSearch> implements PageProfileAdvanceService
{
  private final PageProfileAdvanceRepository pageProfileAdvanceRepository;

  private final PageProfileAdvanceSearch pageProfileAdvanceSearch;

  private final PageProfileAdvanceMapper pageProfileAdvanceMapper;
}
