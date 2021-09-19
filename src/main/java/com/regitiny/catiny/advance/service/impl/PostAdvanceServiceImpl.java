package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.PostAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.PostAdvanceSearch;
import com.regitiny.catiny.advance.service.PostAdvanceService;
import com.regitiny.catiny.advance.service.mapper.PostAdvanceMapper;
import com.regitiny.catiny.domain.Post;
import com.regitiny.catiny.service.PostQueryService;
import com.regitiny.catiny.service.PostService;
import com.regitiny.catiny.service.dto.PostDTO;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class PostAdvanceServiceImpl extends AdvanceService<Post, PostService, PostQueryService, PostAdvanceMapper, PostAdvanceRepository, PostAdvanceSearch> implements PostAdvanceService
{
  private final PostAdvanceRepository postAdvanceRepository;

  private final PostAdvanceSearch postAdvanceSearch;

  private final PostAdvanceMapper postAdvanceMapper;

  @Override
  public Page<PostDTO> getAllPosts(Pageable pageable)
  {
    return postAdvanceRepository.findAll(pageable)
      .map(postAdvanceMapper::e2d);
  }

  @Override
  public Option<PostDTO> uploadPost(PostDTO post)
  {
    var result = postAdvanceRepository.save(postAdvanceMapper.d2e(post));
    return postAdvanceMapper.e2o_d(result);
  }

  @Override
  public void deletePost(UUID id)
  {
    postAdvanceRepository.deleteOneByUuid(id);
  }

  @Override
  public Option<PostDTO> updatePost(PostDTO post)
  {
    return postAdvanceRepository.findOneByUuid(post.getUuid())
      .orElse(Option.of(post.getId()).map(postAdvanceRepository::getById))
      .map(post1 ->
      {
        var result = postAdvanceRepository.save(postAdvanceMapper.d2e(post));
        return postAdvanceMapper.e2d(result);
      });
  }

  @Override
  public Option<PostDTO> partialUpdatePost(PostDTO postDTO)
  {
    return postAdvanceRepository.findOneByUuid(postDTO.getUuid())
      .orElse(Option.of(postDTO.getId()).map(postAdvanceRepository::getById))
      .map(post ->
      {
        postAdvanceMapper.partialUpdate(post, postDTO);
        var result = postAdvanceRepository.save(post);
        return postAdvanceMapper.e2d(result);
      });
  }

  @Override
  public Page<PostDTO> searchPost(String queryString, Pageable pageable)
  {
    return postAdvanceSearch.search(QueryBuilders.prefixQuery("searchField", queryString), pageable)
      .map(postAdvanceMapper::e2d);
  }
}
