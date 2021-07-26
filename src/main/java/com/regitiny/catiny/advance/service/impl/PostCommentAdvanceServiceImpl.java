package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.PostCommentAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.PostCommentAdvanceSearch;
import com.regitiny.catiny.advance.service.PostCommentAdvanceService;
import com.regitiny.catiny.advance.service.mapper.PostCommentAdvanceMapper;
import com.regitiny.catiny.domain.PostComment;
import com.regitiny.catiny.service.PostCommentQueryService;
import com.regitiny.catiny.service.PostCommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class PostCommentAdvanceServiceImpl extends AdvanceService<PostComment, PostCommentService, PostCommentQueryService, PostCommentAdvanceMapper, PostCommentAdvanceRepository, PostCommentAdvanceSearch> implements PostCommentAdvanceService
{
  private final PostCommentAdvanceRepository postCommentAdvanceRepository;

  private final PostCommentAdvanceSearch postCommentAdvanceSearch;

  private final PostCommentAdvanceMapper postCommentAdvanceMapper;
}
