package com.regitiny.catiny.advance.controller.rest.impl;

import com.regitiny.catiny.advance.repository.search.BaseInfoAdvanceSearch;
import com.regitiny.catiny.advance.service.impl.*;
import com.regitiny.catiny.domain.BaseInfo;
import com.regitiny.catiny.util.ApplicationContextUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

@Log4j2
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Transactional
public class TestHelloWorld
{
  private final HistoryUpdateAdvanceServiceImpl historyUpdateAdvanceService;
  private final BaseInfoAdvanceServiceImpl baseInfoAdvanceService;
  private final AccountStatusAdvanceServiceImpl accountStatusAdvanceService;
  private final GroupProfileAdvanceServiceImpl groupProfileAdvanceService;
  private final PostAdvanceServiceImpl postAdvanceServiceImpl;
  private final BaseInfoAdvanceSearch baseInfoAdvanceSearch;

  @GetMapping("/test/hello/{id}")
  public void helloWorld(@PathVariable("id") String id)
  {
    try
    {
      var at = ApplicationContextUtil.getApplicationContext().getBean("baseInfoAdvanceSearch");

      var a = at.getClass().getMethod("save", Object.class).invoke(at, new BaseInfo().uuid(UUID.randomUUID()));
      log.debug(a);
    }
    catch (IllegalAccessException e)
    {
      e.printStackTrace();
    }
    catch (InvocationTargetException e)
    {
      e.printStackTrace();
    }
    catch (NoSuchMethodException e)
    {
      e.printStackTrace();
    }

  }
}
