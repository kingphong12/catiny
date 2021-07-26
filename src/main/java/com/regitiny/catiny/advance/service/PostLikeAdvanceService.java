package com.regitiny.catiny.advance.service;

import com.regitiny.catiny.advance.repository.PostLikeAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.PostLikeAdvanceSearch;
import com.regitiny.catiny.advance.service.mapper.PostLikeAdvanceMapper;
import com.regitiny.catiny.domain.PostLike;
import com.regitiny.catiny.service.PostLikeQueryService;
import com.regitiny.catiny.service.PostLikeService;

/**
 * AdvanceService layer for {@link PostLike}.
 *
 * @see PostLikeService is base service generate by jhipster
 */
public interface PostLikeAdvanceService extends BaseSrvice<PostLike, PostLikeService, PostLikeQueryService, PostLikeAdvanceMapper, PostLikeAdvanceRepository, PostLikeAdvanceSearch>
{
}
