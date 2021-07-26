package com.regitiny.catiny.advance.service;

import com.regitiny.catiny.advance.repository.EventAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.EventAdvanceSearch;
import com.regitiny.catiny.advance.service.mapper.EventAdvanceMapper;
import com.regitiny.catiny.domain.Event;
import com.regitiny.catiny.service.EventQueryService;
import com.regitiny.catiny.service.EventService;

/**
 * AdvanceService layer for {@link Event}.
 *
 * @see EventService is base service generate by jhipster
 */
public interface EventAdvanceService extends BaseSrvice<Event, EventService, EventQueryService, EventAdvanceMapper, EventAdvanceRepository, EventAdvanceSearch>
{
}
