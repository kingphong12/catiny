package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.service.dto.BaseInfoDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.regitiny.catiny.domain.BaseInfo}.
 */
@GeneratedByJHipster
public interface BaseInfoService {
  /**
   * Save a baseInfo.
   *
   * @param baseInfoDTO the entity to save.
   * @return the persisted entity.
   */
  BaseInfoDTO save(BaseInfoDTO baseInfoDTO);

  /**
   * Partially updates a baseInfo.
   *
   * @param baseInfoDTO the entity to update partially.
   * @return the persisted entity.
   */
  Optional<BaseInfoDTO> partialUpdate(BaseInfoDTO baseInfoDTO);

  /**
   * Get all the baseInfos.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<BaseInfoDTO> findAll(Pageable pageable);

  /**
   * Get the "id" baseInfo.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<BaseInfoDTO> findOne(Long id);

  /**
   * Delete the "id" baseInfo.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);

  /**
   * Search for the baseInfo corresponding to the query.
   *
   * @param query the query of the search.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<BaseInfoDTO> search(String query, Pageable pageable);
}
