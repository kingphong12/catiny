package com.regitiny.catiny.web.rest;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.repository.AlbumRepository;
import com.regitiny.catiny.service.AlbumQueryService;
import com.regitiny.catiny.service.AlbumService;
import com.regitiny.catiny.service.criteria.AlbumCriteria;
import com.regitiny.catiny.service.dto.AlbumDTO;
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
 * REST controller for managing {@link com.regitiny.catiny.domain.Album}.
 */
@RestController
@RequestMapping("/api")
@GeneratedByJHipster
public class AlbumResource {

  private final Logger log = LoggerFactory.getLogger(AlbumResource.class);

  private static final String ENTITY_NAME = "album";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final AlbumService albumService;

  private final AlbumRepository albumRepository;

  private final AlbumQueryService albumQueryService;

  public AlbumResource(AlbumService albumService, AlbumRepository albumRepository, AlbumQueryService albumQueryService) {
    this.albumService = albumService;
    this.albumRepository = albumRepository;
    this.albumQueryService = albumQueryService;
  }

  /**
   * {@code POST  /albums} : Create a new album.
   *
   * @param albumDTO the albumDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new albumDTO, or with status {@code 400 (Bad Request)} if the album has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/albums")
  public ResponseEntity<AlbumDTO> createAlbum(@Valid @RequestBody AlbumDTO albumDTO) throws URISyntaxException {
    log.debug("REST request to save Album : {}", albumDTO);
    if (albumDTO.getId() != null) {
      throw new BadRequestAlertException("A new album cannot already have an ID", ENTITY_NAME, "idexists");
    }
    AlbumDTO result = albumService.save(albumDTO);
    return ResponseEntity
      .created(new URI("/api/albums/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /albums/:id} : Updates an existing album.
   *
   * @param id the id of the albumDTO to save.
   * @param albumDTO the albumDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated albumDTO,
   * or with status {@code 400 (Bad Request)} if the albumDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the albumDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/albums/{id}")
  public ResponseEntity<AlbumDTO> updateAlbum(
    @PathVariable(value = "id", required = false) final Long id,
    @Valid @RequestBody AlbumDTO albumDTO
  ) throws URISyntaxException {
    log.debug("REST request to update Album : {}, {}", id, albumDTO);
    if (albumDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, albumDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!albumRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    AlbumDTO result = albumService.save(albumDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, albumDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /albums/:id} : Partial updates given fields of an existing album, field will ignore if it is null
   *
   * @param id the id of the albumDTO to save.
   * @param albumDTO the albumDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated albumDTO,
   * or with status {@code 400 (Bad Request)} if the albumDTO is not valid,
   * or with status {@code 404 (Not Found)} if the albumDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the albumDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/albums/{id}", consumes = { "application/json", "application/merge-patch+json" })
  public ResponseEntity<AlbumDTO> partialUpdateAlbum(
    @PathVariable(value = "id", required = false) final Long id,
    @NotNull @RequestBody AlbumDTO albumDTO
  ) throws URISyntaxException {
    log.debug("REST request to partial update Album partially : {}, {}", id, albumDTO);
    if (albumDTO.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, albumDTO.getId())) {
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
    }

    if (!albumRepository.existsById(id)) {
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
    }

    Optional<AlbumDTO> result = albumService.partialUpdate(albumDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, albumDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /albums} : get all the albums.
   *
   * @param pageable the pagination information.
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of albums in body.
   */
  @GetMapping("/albums")
  public ResponseEntity<List<AlbumDTO>> getAllAlbums(AlbumCriteria criteria, Pageable pageable) {
    log.debug("REST request to get Albums by criteria: {}", criteria);
    Page<AlbumDTO> page = albumQueryService.findByCriteria(criteria, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

  /**
   * {@code GET  /albums/count} : count all the albums.
   *
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
   */
  @GetMapping("/albums/count")
  public ResponseEntity<Long> countAlbums(AlbumCriteria criteria) {
    log.debug("REST request to count Albums by criteria: {}", criteria);
    return ResponseEntity.ok().body(albumQueryService.countByCriteria(criteria));
  }

  /**
   * {@code GET  /albums/:id} : get the "id" album.
   *
   * @param id the id of the albumDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the albumDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/albums/{id}")
  public ResponseEntity<AlbumDTO> getAlbum(@PathVariable Long id) {
    log.debug("REST request to get Album : {}", id);
    Optional<AlbumDTO> albumDTO = albumService.findOne(id);
    return ResponseUtil.wrapOrNotFound(albumDTO);
  }

  /**
   * {@code DELETE  /albums/:id} : delete the "id" album.
   *
   * @param id the id of the albumDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/albums/{id}")
  public ResponseEntity<Void> deleteAlbum(@PathVariable Long id) {
    log.debug("REST request to delete Album : {}", id);
    albumService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }

  /**
   * {@code SEARCH  /_search/albums?query=:query} : search for the album corresponding
   * to the query.
   *
   * @param query the query of the album search.
   * @param pageable the pagination information.
   * @return the result of the search.
   */
  @GetMapping("/_search/albums")
  public ResponseEntity<List<AlbumDTO>> searchAlbums(@RequestParam String query, Pageable pageable) {
    log.debug("REST request to search for a page of Albums for query {}", query);
    Page<AlbumDTO> page = albumService.search(query, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }
}
