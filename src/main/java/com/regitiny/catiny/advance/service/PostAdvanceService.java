package com.regitiny.catiny.advance.service;

import com.regitiny.catiny.advance.repository.PostAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.PostAdvanceSearch;
import com.regitiny.catiny.advance.service.mapper.PostAdvanceMapper;
import com.regitiny.catiny.domain.Post;
import com.regitiny.catiny.service.PostQueryService;
import com.regitiny.catiny.service.PostService;
import com.regitiny.catiny.service.dto.PostDTO;
import io.vavr.control.Option;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

/**
 * AdvanceService layer for {@link Post}.
 *
 * @see PostService is base service generate by jhipster
 */
public interface PostAdvanceService extends BaseSrvice<Post, PostService, PostQueryService, PostAdvanceMapper, PostAdvanceRepository, PostAdvanceSearch>
{
  Page<PostDTO> getAllPosts(Pageable pageable);


  Option<PostDTO> uploadPost(PostDTO postDTO);


  void deletePost(UUID id);


  Option<PostDTO> updatePost(PostDTO postDTO);


  Option<PostDTO> partialUpdatePost(PostDTO postDTO);


  Page<PostDTO> searchPost(String query, Pageable pageable);
}
