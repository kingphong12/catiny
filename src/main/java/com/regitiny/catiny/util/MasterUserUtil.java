package com.regitiny.catiny.util;

import com.regitiny.catiny.advance.repository.MasterUserAdvanceRepository;
import com.regitiny.catiny.advance.service.impl.MasterUserAdvanceServiceImpl;
import com.regitiny.catiny.domain.MasterUser;
import com.regitiny.catiny.domain.User;
import com.regitiny.catiny.repository.UserRepository;
import com.regitiny.catiny.security.SecurityUtils;
import io.vavr.control.Option;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
@Log4j2
public class MasterUserUtil
{
  private static MasterUserAdvanceServiceImpl masterUserAdvanceService;
  private static UserRepository userRepository = null;
  private static MasterUserAdvanceRepository masterUserAdvanceRepository;

  public static Option<MasterUser> getAnonymousMasterUser()
  {
    return masterUserAdvanceService.getAnonymousMasterUser();
  }

  public static Option<MasterUser> getCurrentMasterUser()
  {
    return masterUserAdvanceService.getCurrentMasterUser();
  }

  public static Option<User> getCurrentUser()
  {
    return Option.of(SecurityUtils.getCurrentUserLogin().flatMap(userLogin -> userRepository.findOneByLogin(userLogin)).orElse(null));
  }

  public static Option<User> getUserById(Long id)
  {
    return Option.ofOptional(userRepository.findById(id));
  }

  public static Option<MasterUser> getMasterUserById(Long id)
  {
    return Option.ofOptional(masterUserAdvanceRepository.findById(id));
  }

  public static Option<MasterUser> getMasterUserByUuid(UUID uuid)
  {
    return masterUserAdvanceRepository.findOneByUuid(uuid);
  }

  public static Option<MasterUser> getMasterUserByUserLogin(String login)
  {
    return masterUserAdvanceRepository.findOneByUserLogin(login);
  }

  public static Option<User> getUserByLogin(String login)
  {
    return getMasterUserByUserLogin(login).map(MasterUser::getUser);
  }

  @Autowired
  private void setMasterUserRepository(MasterUserAdvanceServiceImpl masterUserAdvanceService, UserRepository userRepository, MasterUserAdvanceRepository masterUserAdvanceRepository)
  {
    MasterUserUtil.masterUserAdvanceService = masterUserAdvanceService;
    MasterUserUtil.userRepository = userRepository;
    MasterUserUtil.masterUserAdvanceRepository = masterUserAdvanceRepository;
  }



}
