package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.MessageGroupAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.MessageGroupAdvanceSearch;
import com.regitiny.catiny.advance.service.MessageGroupAdvanceService;
import com.regitiny.catiny.advance.service.mapper.MessageGroupAdvanceMapper;
import com.regitiny.catiny.domain.MessageGroup;
import com.regitiny.catiny.service.MessageGroupQueryService;
import com.regitiny.catiny.service.MessageGroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class MessageGroupAdvanceServiceImpl extends AdvanceService<MessageGroup, MessageGroupService, MessageGroupQueryService, MessageGroupAdvanceMapper, MessageGroupAdvanceRepository, MessageGroupAdvanceSearch> implements MessageGroupAdvanceService
{
  private final MessageGroupAdvanceRepository messageGroupAdvanceRepository;

  private final MessageGroupAdvanceSearch messageGroupAdvanceSearch;

  private final MessageGroupAdvanceMapper messageGroupAdvanceMapper;
}
