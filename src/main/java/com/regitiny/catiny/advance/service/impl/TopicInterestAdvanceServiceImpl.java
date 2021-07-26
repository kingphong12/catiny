package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.TopicInterestAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.TopicInterestAdvanceSearch;
import com.regitiny.catiny.advance.service.TopicInterestAdvanceService;
import com.regitiny.catiny.advance.service.mapper.TopicInterestAdvanceMapper;
import com.regitiny.catiny.domain.TopicInterest;
import com.regitiny.catiny.service.TopicInterestQueryService;
import com.regitiny.catiny.service.TopicInterestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class TopicInterestAdvanceServiceImpl extends AdvanceService<TopicInterest, TopicInterestService, TopicInterestQueryService, TopicInterestAdvanceMapper, TopicInterestAdvanceRepository, TopicInterestAdvanceSearch> implements TopicInterestAdvanceService
{
  private final TopicInterestAdvanceRepository topicInterestAdvanceRepository;

  private final TopicInterestAdvanceSearch topicInterestAdvanceSearch;

  private final TopicInterestAdvanceMapper topicInterestAdvanceMapper;
}
