package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.PermissionAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.PermissionAdvanceSearch;
import com.regitiny.catiny.advance.service.PermissionAdvanceService;
import com.regitiny.catiny.advance.service.mapper.PermissionAdvanceMapper;
import com.regitiny.catiny.domain.BaseInfo;
import com.regitiny.catiny.domain.MasterUser;
import com.regitiny.catiny.domain.Permission;
import com.regitiny.catiny.service.PermissionQueryService;
import com.regitiny.catiny.service.PermissionService;
import com.regitiny.catiny.util.MasterUserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class PermissionAdvanceServiceImpl extends AdvanceService<Permission, PermissionService, PermissionQueryService, PermissionAdvanceMapper, PermissionAdvanceRepository, PermissionAdvanceSearch> implements PermissionAdvanceService
{
  private final PermissionAdvanceRepository permissionAdvanceRepository;
  private final PermissionAdvanceSearch permissionAdvanceSearch;
  private final PermissionAdvanceMapper permissionAdvanceMapper;


  /**
   * .read(true)
   * .write(true)
   * .share(true)
   * .add(true)
   * .delete(true)
   * .level(0))
   *
   * @return permission
   */
  public Permission createForOwner()
  {
    var permission = new Permission().uuid(UUID.randomUUID())
      .read(true)
      .write(true)
      .share(true)
      .add(true)
      .delete(true)
      .level(0);
    MasterUserUtils.getCurrentMasterUser().forEach(permission::owner);
    return permissionAdvanceRepository.save(permission);
  }

  /**
   * .read(false)
   * .write(false)
   * .share(false)
   * .add(false)
   * .delete(false)
   * .level(Integer.MAX_VALUE))
   *
   * @return permission
   */
  public Permission createForAnonymous()
  {
    var permission = new Permission().uuid(UUID.randomUUID())
      .read(false)
      .write(false)
      .share(false)
      .add(false)
      .delete(false)
      .level(Integer.MAX_VALUE);
    MasterUserUtils.getAnonymousMasterUser().forEach(permission::owner);
    return permissionAdvanceRepository.save(permission);
  }

  /**
   * .read(true)
   * .write(false)
   * .share(false)
   * .add(false)
   * .delete(false)
   * .level(Integer.MAX_VALUE)
   * .owner(inputOwner)
   *
   * @return permission
   */
  public Permission addUserReadOnly(BaseInfo baseInfoSource, MasterUser owner)
  {
    return permissionAdvanceRepository.save(createForAnonymous().uuid(UUID.randomUUID())
      .read(true)
      .write(false)
      .share(false)
      .add(false)
      .delete(false)
      .level(Integer.MAX_VALUE)
      .owner(owner)
      .baseInfo(baseInfoSource));
  }


}
