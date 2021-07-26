package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.FollowPageAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.FollowPageAdvanceSearch;
import com.regitiny.catiny.advance.service.FollowPageAdvanceService;
import com.regitiny.catiny.advance.service.mapper.FollowPageAdvanceMapper;
import com.regitiny.catiny.domain.FollowPage;
import com.regitiny.catiny.service.FollowPageQueryService;
import com.regitiny.catiny.service.FollowPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class FollowPageAdvanceServiceImpl extends AdvanceService<FollowPage, FollowPageService, FollowPageQueryService, FollowPageAdvanceMapper, FollowPageAdvanceRepository, FollowPageAdvanceSearch> implements FollowPageAdvanceService
{
  private final FollowPageAdvanceRepository followPageAdvanceRepository;

  private final FollowPageAdvanceSearch followPageAdvanceSearch;

  private final FollowPageAdvanceMapper followPageAdvanceMapper;
}
