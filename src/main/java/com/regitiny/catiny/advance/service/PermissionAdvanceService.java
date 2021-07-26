package com.regitiny.catiny.advance.service;

import com.regitiny.catiny.advance.repository.PermissionAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.PermissionAdvanceSearch;
import com.regitiny.catiny.advance.service.mapper.PermissionAdvanceMapper;
import com.regitiny.catiny.domain.Permission;
import com.regitiny.catiny.service.PermissionQueryService;
import com.regitiny.catiny.service.PermissionService;

/**
 * AdvanceService layer for {@link Permission}.
 *
 * @see PermissionService is base service generate by jhipster
 */
public interface PermissionAdvanceService extends BaseSrvice<Permission, PermissionService, PermissionQueryService, PermissionAdvanceMapper, PermissionAdvanceRepository, PermissionAdvanceSearch>
{
}
