package com.regitiny.catiny.advance.service;

import com.regitiny.catiny.advance.repository.ClassInfoAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.ClassInfoAdvanceSearch;
import com.regitiny.catiny.advance.service.mapper.ClassInfoAdvanceMapper;
import com.regitiny.catiny.domain.ClassInfo;
import com.regitiny.catiny.service.ClassInfoQueryService;
import com.regitiny.catiny.service.ClassInfoService;

/**
 * AdvanceService layer for {@link ClassInfo}.
 *
 * @see ClassInfoService is base service generate by jhipster
 */
public interface ClassInfoAdvanceService extends BaseSrvice<ClassInfo, ClassInfoService, ClassInfoQueryService, ClassInfoAdvanceMapper, ClassInfoAdvanceRepository, ClassInfoAdvanceSearch>
{
}
