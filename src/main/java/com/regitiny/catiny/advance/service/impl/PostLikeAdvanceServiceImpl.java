package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.PostLikeAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.PostLikeAdvanceSearch;
import com.regitiny.catiny.advance.service.PostLikeAdvanceService;
import com.regitiny.catiny.advance.service.mapper.PostLikeAdvanceMapper;
import com.regitiny.catiny.domain.PostLike;
import com.regitiny.catiny.service.PostLikeQueryService;
import com.regitiny.catiny.service.PostLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class PostLikeAdvanceServiceImpl extends AdvanceService<PostLike, PostLikeService, PostLikeQueryService, PostLikeAdvanceMapper, PostLikeAdvanceRepository, PostLikeAdvanceSearch> implements PostLikeAdvanceService
{
  private final PostLikeAdvanceRepository postLikeAdvanceRepository;

  private final PostLikeAdvanceSearch postLikeAdvanceSearch;

  private final PostLikeAdvanceMapper postLikeAdvanceMapper;
}
