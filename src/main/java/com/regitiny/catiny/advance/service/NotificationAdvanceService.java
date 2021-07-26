package com.regitiny.catiny.advance.service;

import com.regitiny.catiny.advance.repository.NotificationAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.NotificationAdvanceSearch;
import com.regitiny.catiny.advance.service.mapper.NotificationAdvanceMapper;
import com.regitiny.catiny.domain.Notification;
import com.regitiny.catiny.service.NotificationQueryService;
import com.regitiny.catiny.service.NotificationService;

/**
 * AdvanceService layer for {@link Notification}.
 *
 * @see NotificationService is base service generate by jhipster
 */
public interface NotificationAdvanceService extends BaseSrvice<Notification, NotificationService, NotificationQueryService, NotificationAdvanceMapper, NotificationAdvanceRepository, NotificationAdvanceSearch>
{
}
