package com.regitiny.catiny.advance.service;

import com.regitiny.catiny.advance.repository.MessageGroupAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.MessageGroupAdvanceSearch;
import com.regitiny.catiny.advance.service.mapper.MessageGroupAdvanceMapper;
import com.regitiny.catiny.domain.MessageGroup;
import com.regitiny.catiny.service.MessageGroupQueryService;
import com.regitiny.catiny.service.MessageGroupService;

/**
 * AdvanceService layer for {@link MessageGroup}.
 *
 * @see MessageGroupService is base service generate by jhipster
 */
public interface MessageGroupAdvanceService extends BaseSrvice<MessageGroup, MessageGroupService, MessageGroupQueryService, MessageGroupAdvanceMapper, MessageGroupAdvanceRepository, MessageGroupAdvanceSearch>
{
}
