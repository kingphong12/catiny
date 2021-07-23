package com.regitiny.catiny.advance.service;

import com.regitiny.catiny.advance.repository.MasterUserAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.MasterUserAdvanceSearch;
import com.regitiny.catiny.advance.service.mapper.MasterUserAdvanceMapper;
import com.regitiny.catiny.domain.MasterUser;
import com.regitiny.catiny.service.MasterUserQueryService;
import com.regitiny.catiny.service.MasterUserService;
import io.vavr.control.Option;

/**
 * AdvanceService layer for {@link MasterUser}.
 *
 * @see MasterUserService is base service generate by jhipster
 */
public interface MasterUserAdvanceService extends BaseSrvice<MasterUser, MasterUserService, MasterUserQueryService, MasterUserAdvanceMapper, MasterUserAdvanceRepository, MasterUserAdvanceSearch>
{
  /**
   * use to create MasterUser when register
   *
   * @param login = User.login
   * @return MasterUser registered.
   */
  Option<MasterUser> createMasterUser(String login);
}
