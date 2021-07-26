package com.regitiny.catiny.advance.service;

import com.regitiny.catiny.advance.repository.TopicInterestAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.TopicInterestAdvanceSearch;
import com.regitiny.catiny.advance.service.mapper.TopicInterestAdvanceMapper;
import com.regitiny.catiny.domain.TopicInterest;
import com.regitiny.catiny.service.TopicInterestQueryService;
import com.regitiny.catiny.service.TopicInterestService;

/**
 * AdvanceService layer for {@link TopicInterest}.
 *
 * @see TopicInterestService is base service generate by jhipster
 */
public interface TopicInterestAdvanceService extends BaseSrvice<TopicInterest, TopicInterestService, TopicInterestQueryService, TopicInterestAdvanceMapper, TopicInterestAdvanceRepository, TopicInterestAdvanceSearch>
{
}
