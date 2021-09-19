package com.regitiny.catiny.advance.controller.impl;

import com.regitiny.catiny.advance.controller.rest.PostManagement;
import com.regitiny.catiny.advance.service.PostAdvanceService;
import com.regitiny.catiny.service.dto.PostDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.PaginationUtil;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Log4j2
@RestController
@RequiredArgsConstructor
public class PostManagementImpl implements PostManagement
{
  private final PostAdvanceService postAdvanceService;

  @Override
  public ResponseEntity<List<PostDTO>> getAllPosts(Pageable pageable)
  {
    var page = postAdvanceService.getAllPosts(pageable);
    var headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

  @Override
  public ResponseEntity<PostDTO> uploadPost(PostDTO postDTO)
  {
    return postAdvanceService.uploadPost(postDTO).map(result -> ResponseEntity.status(HttpStatus.CREATED).body(result))
      .getOrElse(ResponseEntity.badRequest().build());
  }

  @Override
  public void deletePost(UUID id)
  {
    postAdvanceService.deletePost(id);
  }

  @Override
  public ResponseEntity<PostDTO> updatePost(UUID id, PostDTO postDTO)
  {
    if (Objects.isNull(postDTO.getId()) && Objects.isNull(postDTO.getUuid()) && Objects.nonNull(id))
      postDTO.setUuid(id);
    else
      return ResponseEntity.notFound().build();
    return postAdvanceService.updatePost(postDTO).map(ResponseEntity::ok)
      .getOrElse(ResponseEntity.badRequest().build());
  }

  @Override
  public ResponseEntity<PostDTO> partialUpdatePost(UUID id, PostDTO postDTO)
  {
    if (Objects.isNull(postDTO.getId()) && Objects.isNull(postDTO.getUuid()) && Objects.nonNull(id))
      postDTO.setUuid(id);
    return postAdvanceService.partialUpdatePost(postDTO).map(ResponseEntity::ok)
      .getOrElse(ResponseEntity.badRequest().build());
  }

  @Override
  public ResponseEntity<List<PostDTO>> searchPost(String query, Pageable pageable)
  {
    var page = postAdvanceService.searchPost(query, pageable);
    var headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

}
