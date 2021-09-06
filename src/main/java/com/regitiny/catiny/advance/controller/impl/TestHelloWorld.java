package com.regitiny.catiny.advance.controller.impl;

import com.regitiny.catiny.advance.repository.search.BaseInfoAdvanceSearch;
import com.regitiny.catiny.advance.service.impl.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


  @GetMapping("/test/hello/{x}")
  public void helloWorld(@PathVariable String x)
  {


  }
}
