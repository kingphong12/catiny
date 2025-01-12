package com.regitiny.catiny.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.Album;
import com.regitiny.catiny.repository.AlbumRepository;
import com.regitiny.catiny.repository.search.AlbumSearchRepository;
import com.regitiny.catiny.service.AlbumService;
import com.regitiny.catiny.service.dto.AlbumDTO;
import com.regitiny.catiny.service.mapper.AlbumMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Album}.
 */
@Service
@Transactional
@GeneratedByJHipster
public class AlbumServiceImpl implements AlbumService {

  private final Logger log = LoggerFactory.getLogger(AlbumServiceImpl.class);

  private final AlbumRepository albumRepository;

  private final AlbumMapper albumMapper;

  private final AlbumSearchRepository albumSearchRepository;

  public AlbumServiceImpl(AlbumRepository albumRepository, AlbumMapper albumMapper, AlbumSearchRepository albumSearchRepository) {
    this.albumRepository = albumRepository;
    this.albumMapper = albumMapper;
    this.albumSearchRepository = albumSearchRepository;
  }

  @Override
  public AlbumDTO save(AlbumDTO albumDTO) {
    log.debug("Request to save Album : {}", albumDTO);
    Album album = albumMapper.toEntity(albumDTO);
    album = albumRepository.save(album);
    AlbumDTO result = albumMapper.toDto(album);
    albumSearchRepository.save(album);
    return result;
  }

  @Override
  public Optional<AlbumDTO> partialUpdate(AlbumDTO albumDTO) {
    log.debug("Request to partially update Album : {}", albumDTO);

    return albumRepository
      .findById(albumDTO.getId())
      .map(existingAlbum -> {
        albumMapper.partialUpdate(existingAlbum, albumDTO);

        return existingAlbum;
      })
      .map(albumRepository::save)
      .map(savedAlbum -> {
        albumSearchRepository.save(savedAlbum);

        return savedAlbum;
      })
      .map(albumMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<AlbumDTO> findAll(Pageable pageable) {
    log.debug("Request to get all Albums");
    return albumRepository.findAll(pageable).map(albumMapper::toDto);
  }

  public Page<AlbumDTO> findAllWithEagerRelationships(Pageable pageable) {
    return albumRepository.findAllWithEagerRelationships(pageable).map(albumMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<AlbumDTO> findOne(Long id) {
    log.debug("Request to get Album : {}", id);
    return albumRepository.findOneWithEagerRelationships(id).map(albumMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete Album : {}", id);
    albumRepository.deleteById(id);
    albumSearchRepository.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<AlbumDTO> search(String query, Pageable pageable) {
    log.debug("Request to search for a page of Albums for query {}", query);
    return albumSearchRepository.search(query, pageable).map(albumMapper::toDto);
  }
}
