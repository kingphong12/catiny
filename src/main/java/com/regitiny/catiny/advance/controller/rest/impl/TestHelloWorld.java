package com.regitiny.catiny.advance.controller.rest.impl;

import com.regitiny.catiny.advance.service.impl.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
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

  @GetMapping("/test/hello")
  public void helloWorld()
  {
//    var a = accountStatusAdvanceService.publicLocal().advanceRepository.findById(1L).get();
////    a=accountStatusAdvanceService.publicLocal().advanceMapper.cleanEntity(a);
//    var json = new JSONObject();
//    json.put("hihi" ,new Gson().toJson(a));
//    log.debug(json.toString());
    postAdvanceServiceImpl.a();


  }
}
