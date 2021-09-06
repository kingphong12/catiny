package com.regitiny.catiny.advance.controller.rest;

import com.regitiny.catiny.service.dto.PostDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * BASE_PATH: {@value BASE_PATH}
 * <p>
 * post management api
 */
@RequestMapping(PostManagement.BASE_PATH)
public interface PostManagement
{
  String BASE_PATH = "/api/o/posts"; //NOSONAR


  /**
   * GET : /api/o/posts
   */
  @GetMapping
  ResponseEntity<List<PostDTO>> getAllPosts(Pageable pageable);


  @PostMapping
  ResponseEntity<PostDTO> uploadPost(PostDTO postDTO);


  @DeleteMapping("/{id}")
  void deletePost(@PathVariable UUID id);


  @PutMapping("/{id}")
  ResponseEntity<PostDTO> updatePost(@PathVariable UUID id, @RequestBody PostDTO postDTO);


  @PatchMapping("/{id}")
  ResponseEntity<PostDTO> partialUpdatePost(@PathVariable UUID id, @RequestBody PostDTO postDTO);


  @GetMapping("/_search")
  ResponseEntity<List<PostDTO>> searchPost(String query, Pageable pageable);


}
