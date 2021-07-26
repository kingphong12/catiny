package com.regitiny.catiny.advance.service;

import com.regitiny.catiny.advance.repository.HistoryUpdateAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.HistoryUpdateAdvanceSearch;
import com.regitiny.catiny.advance.service.mapper.HistoryUpdateAdvanceMapper;
import com.regitiny.catiny.domain.HistoryUpdate;
import com.regitiny.catiny.service.HistoryUpdateQueryService;
import com.regitiny.catiny.service.HistoryUpdateService;

/**
 * AdvanceService layer for {@link HistoryUpdate}.
 *
 * @see HistoryUpdateService is base service generate by jhipster
 */
public interface HistoryUpdateAdvanceService extends BaseSrvice<HistoryUpdate, HistoryUpdateService, HistoryUpdateQueryService, HistoryUpdateAdvanceMapper, HistoryUpdateAdvanceRepository, HistoryUpdateAdvanceSearch>
{
}
