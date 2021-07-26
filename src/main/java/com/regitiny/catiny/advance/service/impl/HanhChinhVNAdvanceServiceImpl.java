package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.HanhChinhVNAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.HanhChinhVNAdvanceSearch;
import com.regitiny.catiny.advance.service.HanhChinhVNAdvanceService;
import com.regitiny.catiny.advance.service.mapper.HanhChinhVNAdvanceMapper;
import com.regitiny.catiny.domain.HanhChinhVN;
import com.regitiny.catiny.service.HanhChinhVNQueryService;
import com.regitiny.catiny.service.HanhChinhVNService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class HanhChinhVNAdvanceServiceImpl extends AdvanceService<HanhChinhVN, HanhChinhVNService, HanhChinhVNQueryService, HanhChinhVNAdvanceMapper, HanhChinhVNAdvanceRepository, HanhChinhVNAdvanceSearch> implements HanhChinhVNAdvanceService
{
  private final HanhChinhVNAdvanceRepository hanhChinhVNAdvanceRepository;

  private final HanhChinhVNAdvanceSearch hanhChinhVNAdvanceSearch;

  private final HanhChinhVNAdvanceMapper hanhChinhVNAdvanceMapper;
}
