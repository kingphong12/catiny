package com.regitiny.catiny.advance.controller.impl;

import com.regitiny.catiny.advance.repository.MasterUserAdvanceRepository;
import com.regitiny.catiny.advance.service.mapper.MasterUserAdvanceMapper;
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
  private final MasterUserAdvanceRepository masterUserAdvanceRepository;
  private final MasterUserAdvanceMapper masterUserAdvanceMapper;

  @GetMapping("/test/hello/{x}")
  public void helloWorld(@PathVariable String x)
  {
    masterUserAdvanceRepository.findAll().stream().map(masterUserAdvanceMapper::e2Plus)
      .map(p -> p.addByRegex("topicInterest.[uuid]").getTarget()).forEach(masterUser ->
      {
        log.debug(masterUser);

      });

  }
}
