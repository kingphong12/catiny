package com.regitiny.catiny.advance.service;

import com.regitiny.catiny.advance.repository.MessageContentAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.MessageContentAdvanceSearch;
import com.regitiny.catiny.advance.service.mapper.MessageContentAdvanceMapper;
import com.regitiny.catiny.domain.MessageContent;
import com.regitiny.catiny.service.MessageContentQueryService;
import com.regitiny.catiny.service.MessageContentService;

/**
 * AdvanceService layer for {@link MessageContent}.
 *
 * @see MessageContentService is base service generate by jhipster
 */
public interface MessageContentAdvanceService extends BaseSrvice<MessageContent, MessageContentService, MessageContentQueryService, MessageContentAdvanceMapper, MessageContentAdvanceRepository, MessageContentAdvanceSearch>
{
}
