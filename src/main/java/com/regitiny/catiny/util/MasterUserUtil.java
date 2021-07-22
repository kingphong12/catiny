package com.regitiny.catiny.util;

import com.regitiny.catiny.advance.service.impl.MasterUserAdvanceServiceImpl;
import com.regitiny.catiny.domain.MasterUser;
import io.vavr.control.Option;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MasterUserUtil
{
  private static final Logger log = LogManager.getLogger(MasterUserUtil.class);
  private static MasterUserAdvanceServiceImpl masterUserAdvanceService;

  public static Option<MasterUser> getCurrentMasterUser()
  {
    return masterUserAdvanceService.getCurrentMasterUser();
  }

  public static Option<MasterUser> anonymousMasterUser()
  {
    return masterUserAdvanceService.getAnonymousMasterUser();
  }

  @Autowired
  private void setMasterUserRepository(MasterUserAdvanceServiceImpl masterUserAdvanceService)
  {
    MasterUserUtil.masterUserAdvanceService = masterUserAdvanceService;
  }

}
