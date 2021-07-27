package com.regitiny.catiny.advance.controller.rest.impl;

import com.regitiny.catiny.advance.service.impl.AccountStatusAdvanceServiceImpl;
import com.regitiny.catiny.advance.service.impl.BaseInfoAdvanceServiceImpl;
import com.regitiny.catiny.advance.service.impl.GroupProfileAdvanceServiceImpl;
import com.regitiny.catiny.advance.service.impl.HistoryUpdateAdvanceServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TestHelloWorld
{
  private final HistoryUpdateAdvanceServiceImpl historyUpdateAdvanceService;
  private final BaseInfoAdvanceServiceImpl baseInfoAdvanceService;
  private final AccountStatusAdvanceServiceImpl accountStatusAdvanceService;
  private final GroupProfileAdvanceServiceImpl groupProfileAdvanceService;

  @GetMapping("/test/hello")
  public void helloWorld()
  {
    groupProfileAdvanceService.a();

  }
}
