package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.PostAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.PostAdvanceSearch;
import com.regitiny.catiny.advance.service.PostAdvanceService;
import com.regitiny.catiny.advance.service.mapper.PostAdvanceMapper;
import com.regitiny.catiny.domain.Post;
import com.regitiny.catiny.service.PostQueryService;
import com.regitiny.catiny.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class PostAdvanceServiceImpl extends AdvanceService<Post, PostService, PostQueryService, PostAdvanceMapper, PostAdvanceRepository, PostAdvanceSearch> implements PostAdvanceService
{
  private final PostAdvanceRepository postAdvanceRepository;

  private final PostAdvanceSearch postAdvanceSearch;

  private final PostAdvanceMapper postAdvanceMapper;

  public void a()
  {
//    var p = postAdvanceRepository.findById(1L).get();
    var p = new Post().content("dsadasdasdasdasdsadadas");
    p = postAdvanceRepository.save(p.searchField("1234"));
    p = postAdvanceRepository.save(p.searchField("12fdfds"));
    p = postAdvanceRepository.save(p.searchField("dsadsadas"));
    p = postAdvanceRepository.save(p.searchField("dsadassafsđcvdfdsfsdfds"));

  }
}
