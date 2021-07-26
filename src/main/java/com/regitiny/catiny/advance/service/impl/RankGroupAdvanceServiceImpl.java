package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.RankGroupAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.RankGroupAdvanceSearch;
import com.regitiny.catiny.advance.service.RankGroupAdvanceService;
import com.regitiny.catiny.advance.service.mapper.RankGroupAdvanceMapper;
import com.regitiny.catiny.domain.RankGroup;
import com.regitiny.catiny.service.RankGroupQueryService;
import com.regitiny.catiny.service.RankGroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class RankGroupAdvanceServiceImpl extends AdvanceService<RankGroup, RankGroupService, RankGroupQueryService, RankGroupAdvanceMapper, RankGroupAdvanceRepository, RankGroupAdvanceSearch> implements RankGroupAdvanceService
{
  private final RankGroupAdvanceRepository rankGroupAdvanceRepository;

  private final RankGroupAdvanceSearch rankGroupAdvanceSearch;

  private final RankGroupAdvanceMapper rankGroupAdvanceMapper;
}
