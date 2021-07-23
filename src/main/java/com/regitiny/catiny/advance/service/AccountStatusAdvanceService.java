package com.regitiny.catiny.advance.service;

import com.regitiny.catiny.advance.repository.AccountStatusAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.AccountStatusAdvanceSearch;
import com.regitiny.catiny.advance.service.mapper.AccountStatusAdvanceMapper;
import com.regitiny.catiny.domain.AccountStatus;
import com.regitiny.catiny.service.AccountStatusQueryService;
import com.regitiny.catiny.service.AccountStatusService;

/**
 * AdvanceService layer for {@link AccountStatus}.
 *
 * @see AccountStatusService is base service generate by jhipster
 */
public interface AccountStatusAdvanceService extends BaseSrvice<AccountStatus, AccountStatusService, AccountStatusQueryService, AccountStatusAdvanceMapper, AccountStatusAdvanceRepository, AccountStatusAdvanceSearch>
{
}
