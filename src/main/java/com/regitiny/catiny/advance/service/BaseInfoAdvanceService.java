package com.regitiny.catiny.advance.service;

import com.regitiny.catiny.advance.repository.BaseInfoAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.BaseInfoAdvanceSearch;
import com.regitiny.catiny.advance.service.mapper.BaseInfoAdvanceMapper;
import com.regitiny.catiny.domain.BaseInfo;
import com.regitiny.catiny.service.BaseInfoQueryService;
import com.regitiny.catiny.service.BaseInfoService;

/**
 * Spring Data Elasticsearch advance-repository extends jhipster-search-repository for the {@link com.regitiny.catiny.domain.BaseInfo} entityDomain.
 *
 * @see BaseInfoService is base repository generate by jhipster
 */
public interface BaseInfoAdvanceService extends BaseSrvice<BaseInfo, BaseInfoService, BaseInfoQueryService, BaseInfoAdvanceMapper, BaseInfoAdvanceRepository, BaseInfoAdvanceSearch>
{
}
