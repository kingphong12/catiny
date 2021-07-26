package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.FriendAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.FriendAdvanceSearch;
import com.regitiny.catiny.advance.service.FriendAdvanceService;
import com.regitiny.catiny.advance.service.mapper.FriendAdvanceMapper;
import com.regitiny.catiny.domain.Friend;
import com.regitiny.catiny.service.FriendQueryService;
import com.regitiny.catiny.service.FriendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class FriendAdvanceServiceImpl extends AdvanceService<Friend, FriendService, FriendQueryService, FriendAdvanceMapper, FriendAdvanceRepository, FriendAdvanceSearch> implements FriendAdvanceService
{
  private final FriendAdvanceRepository friendAdvanceRepository;

  private final FriendAdvanceSearch friendAdvanceSearch;

  private final FriendAdvanceMapper friendAdvanceMapper;
}
