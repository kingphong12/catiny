package com.regitiny.catiny.web.rest;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.repository.ImageRepository;
import com.regitiny.catiny.service.ImageQueryService;
import com.regitiny.catiny.service.ImageService;
import com.regitiny.catiny.service.criteria.ImageCriteria;
import com.regitiny.catiny.service.dto.ImageDTO;
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
 * REST controller for managing {@link com.regitiny.catiny.domain.Image}.
 */
@RestController
@RequestMapping("/api")
@GeneratedByJHipster
public class ImageResource {

  private final Logger log = LoggerFactory.getLogger(ImageResource.class);

  private static final String ENTITY_NAME = "image";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final ImageService imageService;

  private final ImageRepository imageRepository;

  private final ImageQueryService imageQueryService;

  public ImageResource(ImageService imageService, ImageRepository imageRepository, ImageQueryService imageQueryService) {
    this.imageService = imageService;
    this.imageRepository = imageRepository;
    this.imageQueryService = imageQueryService;
  }

  /**
   * {@code POST  /images} : Create a new image.
   *
   * @param imageDTO the imageDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new imageDTO, or with status {@code 400 (Bad Request)} if the image has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/images")
  public ResponseEntity<ImageDTO> createImage(@Valid @RequestBody ImageDTO imageDTO) throws URISyntaxException {
    log.debug("REST request to save Image : {}", imageDTO);
    if (imageDTO.getId() != null) {
      throw new BadRequestAlertException("A new image cannot already have an ID", ENTITY_NAME, "idexists");
    }
    ImageDTO result = imageService.save(imageDTO);
    return ResponseEntity
      .created(new URI("/api/images/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /images/:id} : Updates an existing image.
   *
   * @param id the id of the imageDTO to save.
   * @param imageDTO the imageDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated imageDTO,
   * or with status {@code 400 (Bad Request)} if the imageDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the imageDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/images/{id}")
  public ResponseEntity<ImageDTO> updateImage(
    @PathVariable(value = "id", required = false) final Long id,
    @Valid @RequestBody ImageDTO imageDTO
  ) throws URISyntaxException {
    log.debug("REST request to update Image : {}, {}", id, imageDTO);
    if (imageDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, imageDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!imageRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    ImageDTO result = imageService.save(imageDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, imageDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /images/:id} : Partial updates given fields of an existing image, field will ignore if it is null
   *
   * @param id the id of the imageDTO to save.
   * @param imageDTO the imageDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated imageDTO,
   * or with status {@code 400 (Bad Request)} if the imageDTO is not valid,
   * or with status {@code 404 (Not Found)} if the imageDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the imageDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/images/{id}", consumes = { "application/json", "application/merge-patch+json" })
  public ResponseEntity<ImageDTO> partialUpdateImage(
    @PathVariable(value = "id", required = false) final Long id,
    @NotNull @RequestBody ImageDTO imageDTO
  ) throws URISyntaxException {
    log.debug("REST request to partial update Image partially : {}, {}", id, imageDTO);
    if (imageDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, imageDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!imageRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    Optional<ImageDTO> result = imageService.partialUpdate(imageDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, imageDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /images} : get all the images.
   *
   * @param pageable the pagination information.
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of images in body.
   */
  @GetMapping("/images")
  public ResponseEntity<List<ImageDTO>> getAllImages(ImageCriteria criteria, Pageable pageable) {
    log.debug("REST request to get Images by criteria: {}", criteria);
    Page<ImageDTO> page = imageQueryService.findByCriteria(criteria, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

  /**
   * {@code GET  /images/count} : count all the images.
   *
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
   */
  @GetMapping("/images/count")
  public ResponseEntity<Long> countImages(ImageCriteria criteria) {
    log.debug("REST request to count Images by criteria: {}", criteria);
    return ResponseEntity.ok().body(imageQueryService.countByCriteria(criteria));
  }

  /**
   * {@code GET  /images/:id} : get the "id" image.
   *
   * @param id the id of the imageDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the imageDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/images/{id}")
  public ResponseEntity<ImageDTO> getImage(@PathVariable Long id) {
    log.debug("REST request to get Image : {}", id);
    Optional<ImageDTO> imageDTO = imageService.findOne(id);
    return ResponseUtil.wrapOrNotFound(imageDTO);
  }

  /**
   * {@code DELETE  /images/:id} : delete the "id" image.
   *
   * @param id the id of the imageDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/images/{id}")
  public ResponseEntity<Void> deleteImage(@PathVariable Long id) {
    log.debug("REST request to delete Image : {}", id);
    imageService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }

  /**
   * {@code SEARCH  /_search/images?query=:query} : search for the image corresponding
   * to the query.
   *
   * @param query the query of the image search.
   * @param pageable the pagination information.
   * @return the result of the search.
   */
  @GetMapping("/_search/images")
  public ResponseEntity<List<ImageDTO>> searchImages(@RequestParam String query, Pageable pageable) {
    log.debug("REST request to search for a page of Images for query {}", query);
    Page<ImageDTO> page = imageService.search(query, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }
}
