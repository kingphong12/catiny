package com.regitiny.catiny.advance.service;

import com.regitiny.catiny.advance.repository.PostAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.PostAdvanceSearch;
import com.regitiny.catiny.advance.service.mapper.PostAdvanceMapper;
import com.regitiny.catiny.domain.Post;
import com.regitiny.catiny.service.PostQueryService;
import com.regitiny.catiny.service.PostService;

/**
 * AdvanceService layer for {@link Post}.
 *
 * @see PostService is base service generate by jhipster
 */
public interface PostAdvanceService extends BaseSrvice<Post, PostService, PostQueryService, PostAdvanceMapper, PostAdvanceRepository, PostAdvanceSearch>
{
}
