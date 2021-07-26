package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.NotificationAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.NotificationAdvanceSearch;
import com.regitiny.catiny.advance.service.NotificationAdvanceService;
import com.regitiny.catiny.advance.service.mapper.NotificationAdvanceMapper;
import com.regitiny.catiny.domain.Notification;
import com.regitiny.catiny.service.NotificationQueryService;
import com.regitiny.catiny.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class NotificationAdvanceServiceImpl extends AdvanceService<Notification, NotificationService, NotificationQueryService, NotificationAdvanceMapper, NotificationAdvanceRepository, NotificationAdvanceSearch> implements NotificationAdvanceService
{
  private final NotificationAdvanceRepository notificationAdvanceRepository;

  private final NotificationAdvanceSearch notificationAdvanceSearch;

  private final NotificationAdvanceMapper notificationAdvanceMapper;
}
