package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.EventAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.EventAdvanceSearch;
import com.regitiny.catiny.advance.service.EventAdvanceService;
import com.regitiny.catiny.advance.service.mapper.EventAdvanceMapper;
import com.regitiny.catiny.domain.Event;
import com.regitiny.catiny.service.EventQueryService;
import com.regitiny.catiny.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class EventAdvanceServiceImpl extends AdvanceService<Event, EventService, EventQueryService, EventAdvanceMapper, EventAdvanceRepository, EventAdvanceSearch> implements EventAdvanceService
{
  private final EventAdvanceRepository eventAdvanceRepository;

  private final EventAdvanceSearch eventAdvanceSearch;

  private final EventAdvanceMapper eventAdvanceMapper;
}
