package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.RankUserAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.RankUserAdvanceSearch;
import com.regitiny.catiny.advance.service.RankUserAdvanceService;
import com.regitiny.catiny.advance.service.mapper.RankUserAdvanceMapper;
import com.regitiny.catiny.domain.RankUser;
import com.regitiny.catiny.service.RankUserQueryService;
import com.regitiny.catiny.service.RankUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class RankUserAdvanceServiceImpl extends AdvanceService<RankUser, RankUserService, RankUserQueryService, RankUserAdvanceMapper, RankUserAdvanceRepository, RankUserAdvanceSearch> implements RankUserAdvanceService
{
  private final RankUserAdvanceRepository rankUserAdvanceRepository;

  private final RankUserAdvanceSearch rankUserAdvanceSearch;

  private final RankUserAdvanceMapper rankUserAdvanceMapper;
}
