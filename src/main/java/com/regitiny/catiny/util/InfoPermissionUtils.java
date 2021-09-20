package com.regitiny.catiny.util;

import com.regitiny.catiny.advance.service.impl.BaseInfoAdvanceServiceImpl;
import com.regitiny.catiny.advance.service.impl.PermissionAdvanceServiceImpl;
import com.regitiny.catiny.domain.BaseInfo;
import com.regitiny.catiny.domain.Permission;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class InfoPermissionUtils
{
  private static BaseInfoAdvanceServiceImpl baseInfoAdvanceService;
  private static PermissionAdvanceServiceImpl permissionAdvanceService;

  @Autowired
  public InfoPermissionUtils(BaseInfoAdvanceServiceImpl baseInfoAdvanceService, PermissionAdvanceServiceImpl permissionAdvanceService)
  {
    InfoPermissionUtils.baseInfoAdvanceService = baseInfoAdvanceService;
    InfoPermissionUtils.permissionAdvanceService = permissionAdvanceService;
  }

  public static void updatePermission(Permission permission)
  {

  }

  public static void updateBaseInfo(BaseInfo baseInfo)
  {

  }

  public static void addNewPermissionToBaseInfo()
  {

  }
}
