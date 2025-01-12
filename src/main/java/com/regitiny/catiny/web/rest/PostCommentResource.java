package com.regitiny.catiny.web.rest;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.repository.PostCommentRepository;
import com.regitiny.catiny.service.PostCommentQueryService;
import com.regitiny.catiny.service.PostCommentService;
import com.regitiny.catiny.service.criteria.PostCommentCriteria;
import com.regitiny.catiny.service.dto.PostCommentDTO;
import com.regitiny.catiny.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.regitiny.catiny.domain.PostComment}.
 */
@RestController
@RequestMapping("/api")
@GeneratedByJHipster
public class PostCommentResource {

  private final Logger log = LoggerFactory.getLogger(PostCommentResource.class);

  private static final String ENTITY_NAME = "postComment";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final PostCommentService postCommentService;

  private final PostCommentRepository postCommentRepository;

  private final PostCommentQueryService postCommentQueryService;

  public PostCommentResource(
    PostCommentService postCommentService,
    PostCommentRepository postCommentRepository,
    PostCommentQueryService postCommentQueryService
  ) {
    this.postCommentService = postCommentService;
    this.postCommentRepository = postCommentRepository;
    this.postCommentQueryService = postCommentQueryService;
  }

  /**
   * {@code POST  /post-comments} : Create a new postComment.
   *
   * @param postCommentDTO the postCommentDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new postCommentDTO, or with status {@code 400 (Bad Request)} if the postComment has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/post-comments")
  public ResponseEntity<PostCommentDTO> createPostComment(@Valid @RequestBody PostCommentDTO postCommentDTO) throws URISyntaxException {
    log.debug("REST request to save PostComment : {}", postCommentDTO);
    if (postCommentDTO.getId() != null) {
      throw new BadRequestAlertException("A new postComment cannot already have an ID", ENTITY_NAME, "idexists");
    }
    PostCommentDTO result = postCommentService.save(postCommentDTO);
    return ResponseEntity
      .created(new URI("/api/post-comments/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /post-comments/:id} : Updates an existing postComment.
   *
   * @param id the id of the postCommentDTO to save.
   * @param postCommentDTO the postCommentDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated postCommentDTO,
   * or with status {@code 400 (Bad Request)} if the postCommentDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the postCommentDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/post-comments/{id}")
  public ResponseEntity<PostCommentDTO> updatePostComment(
    @PathVariable(value = "id", required = false) final Long id,
    @Valid @RequestBody PostCommentDTO postCommentDTO
  ) throws URISyntaxException {
    log.debug("REST request to update PostComment : {}, {}", id, postCommentDTO);
    if (postCommentDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, postCommentDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!postCommentRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    PostCommentDTO result = postCommentService.save(postCommentDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, postCommentDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /post-comments/:id} : Partial updates given fields of an existing postComment, field will ignore if it is null
   *
   * @param id the id of the postCommentDTO to save.
   * @param postCommentDTO the postCommentDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated postCommentDTO,
   * or with status {@code 400 (Bad Request)} if the postCommentDTO is not valid,
   * or with status {@code 404 (Not Found)} if the postCommentDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the postCommentDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/post-comments/{id}", consumes = { "application/json", "application/merge-patch+json" })
  public ResponseEntity<PostCommentDTO> partialUpdatePostComment(
    @PathVariable(value = "id", required = false) final Long id,
    @NotNull @RequestBody PostCommentDTO postCommentDTO
  ) throws URISyntaxException {
    log.debug("REST request to partial update PostComment partially : {}, {}", id, postCommentDTO);
    if (postCommentDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, postCommentDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!postCommentRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    Optional<PostCommentDTO> result = postCommentService.partialUpdate(postCommentDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, postCommentDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /post-comments} : get all the postComments.
   *
   * @param pageable the pagination information.
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of postComments in body.
   */
  @GetMapping("/post-comments")
  public ResponseEntity<List<PostCommentDTO>> getAllPostComments(PostCommentCriteria criteria, Pageable pageable) {
    log.debug("REST request to get PostComments by criteria: {}", criteria);
    Page<PostCommentDTO> page = postCommentQueryService.findByCriteria(criteria, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

  /**
   * {@code GET  /post-comments/count} : count all the postComments.
   *
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
   */
  @GetMapping("/post-comments/count")
  public ResponseEntity<Long> countPostComments(PostCommentCriteria criteria) {
    log.debug("REST request to count PostComments by criteria: {}", criteria);
    return ResponseEntity.ok().body(postCommentQueryService.countByCriteria(criteria));
  }

  /**
   * {@code GET  /post-comments/:id} : get the "id" postComment.
   *
   * @param id the id of the postCommentDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the postCommentDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/post-comments/{id}")
  public ResponseEntity<PostCommentDTO> getPostComment(@PathVariable Long id) {
    log.debug("REST request to get PostComment : {}", id);
    Optional<PostCommentDTO> postCommentDTO = postCommentService.findOne(id);
    return ResponseUtil.wrapOrNotFound(postCommentDTO);
  }

  /**
   * {@code DELETE  /post-comments/:id} : delete the "id" postComment.
   *
   * @param id the id of the postCommentDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/post-comments/{id}")
  public ResponseEntity<Void> deletePostComment(@PathVariable Long id) {
    log.debug("REST request to delete PostComment : {}", id);
    postCommentService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }

  /**
   * {@code SEARCH  /_search/post-comments?query=:query} : search for the postComment corresponding
   * to the query.
   *
   * @param query the query of the postComment search.
   * @param pageable the pagination information.
   * @return the result of the search.
   */
  @GetMapping("/_search/post-comments")
  public ResponseEntity<List<PostCommentDTO>> searchPostComments(@RequestParam String query, Pageable pageable) {
    log.debug("REST request to search for a page of PostComments for query {}", query);
    Page<PostCommentDTO> page = postCommentService.search(query, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }
}
