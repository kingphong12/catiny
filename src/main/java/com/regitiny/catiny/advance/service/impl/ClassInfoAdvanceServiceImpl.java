package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.ClassInfoAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.ClassInfoAdvanceSearch;
import com.regitiny.catiny.advance.service.ClassInfoAdvanceService;
import com.regitiny.catiny.advance.service.mapper.ClassInfoAdvanceMapper;
import com.regitiny.catiny.domain.ClassInfo;
import com.regitiny.catiny.service.ClassInfoQueryService;
import com.regitiny.catiny.service.ClassInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class ClassInfoAdvanceServiceImpl extends AdvanceService<ClassInfo, ClassInfoService, ClassInfoQueryService, ClassInfoAdvanceMapper, ClassInfoAdvanceRepository, ClassInfoAdvanceSearch> implements ClassInfoAdvanceService
{
  private final ClassInfoAdvanceRepository classInfoAdvanceRepository;

  private final ClassInfoAdvanceSearch classInfoAdvanceSearch;

  private final ClassInfoAdvanceMapper classInfoAdvanceMapper;
}
