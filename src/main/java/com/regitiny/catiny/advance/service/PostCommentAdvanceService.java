package com.regitiny.catiny.advance.service;

import com.regitiny.catiny.advance.repository.PostCommentAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.PostCommentAdvanceSearch;
import com.regitiny.catiny.advance.service.mapper.PostCommentAdvanceMapper;
import com.regitiny.catiny.domain.PostComment;
import com.regitiny.catiny.service.PostCommentQueryService;
import com.regitiny.catiny.service.PostCommentService;

/**
 * AdvanceService layer for {@link PostComment}.
 *
 * @see PostCommentService is base service generate by jhipster
 */
public interface PostCommentAdvanceService extends BaseSrvice<PostComment, PostCommentService, PostCommentQueryService, PostCommentAdvanceMapper, PostCommentAdvanceRepository, PostCommentAdvanceSearch>
{
}
