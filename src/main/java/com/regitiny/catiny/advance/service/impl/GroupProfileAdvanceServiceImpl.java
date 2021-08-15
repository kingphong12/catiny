package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.GroupProfileAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.GroupProfileAdvanceSearch;
import com.regitiny.catiny.advance.service.GroupProfileAdvanceService;
import com.regitiny.catiny.advance.service.mapper.GroupProfileAdvanceMapper;
import com.regitiny.catiny.domain.GroupProfile;
import com.regitiny.catiny.service.GroupProfileQueryService;
import com.regitiny.catiny.service.GroupProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class GroupProfileAdvanceServiceImpl extends AdvanceService<GroupProfile, GroupProfileService, GroupProfileQueryService, GroupProfileAdvanceMapper, GroupProfileAdvanceRepository, GroupProfileAdvanceSearch> implements GroupProfileAdvanceService
{
  private final GroupProfileAdvanceRepository groupProfileAdvanceRepository;

  private final GroupProfileAdvanceSearch groupProfileAdvanceSearch;

  private final GroupProfileAdvanceMapper groupProfileAdvanceMapper;
}
