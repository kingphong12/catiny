package com.regitiny.catiny.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.BaseInfo;
import com.regitiny.catiny.repository.BaseInfoRepository;
import com.regitiny.catiny.repository.search.BaseInfoSearchRepository;
import com.regitiny.catiny.service.BaseInfoService;
import com.regitiny.catiny.service.dto.BaseInfoDTO;
import com.regitiny.catiny.service.mapper.BaseInfoMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link BaseInfo}.
 */
@Service
@Transactional
@GeneratedByJHipster
public class BaseInfoServiceImpl implements BaseInfoService {

  private final Logger log = LoggerFactory.getLogger(BaseInfoServiceImpl.class);

  private final BaseInfoRepository baseInfoRepository;

  private final BaseInfoMapper baseInfoMapper;

  private final BaseInfoSearchRepository baseInfoSearchRepository;

  public BaseInfoServiceImpl(
    BaseInfoRepository baseInfoRepository,
    BaseInfoMapper baseInfoMapper,
    BaseInfoSearchRepository baseInfoSearchRepository
  ) {
    this.baseInfoRepository = baseInfoRepository;
    this.baseInfoMapper = baseInfoMapper;
    this.baseInfoSearchRepository = baseInfoSearchRepository;
  }

  @Override
  public BaseInfoDTO save(BaseInfoDTO baseInfoDTO) {
    log.debug("Request to save BaseInfo : {}", baseInfoDTO);
    BaseInfo baseInfo = baseInfoMapper.toEntity(baseInfoDTO);
    baseInfo = baseInfoRepository.save(baseInfo);
    BaseInfoDTO result = baseInfoMapper.toDto(baseInfo);
    baseInfoSearchRepository.save(baseInfo);
    return result;
  }

  @Override
  public Optional<BaseInfoDTO> partialUpdate(BaseInfoDTO baseInfoDTO) {
    log.debug("Request to partially update BaseInfo : {}", baseInfoDTO);

    return baseInfoRepository
      .findById(baseInfoDTO.getId())
      .map(
        existingBaseInfo -> {
          baseInfoMapper.partialUpdate(existingBaseInfo, baseInfoDTO);

          return existingBaseInfo;
        }
      )
      .map(baseInfoRepository::save)
      .map(
        savedBaseInfo -> {
          baseInfoSearchRepository.save(savedBaseInfo);

          return savedBaseInfo;
        }
      )
      .map(baseInfoMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<BaseInfoDTO> findAll(Pageable pageable) {
    log.debug("Request to get all BaseInfos");
    return baseInfoRepository.findAll(pageable).map(baseInfoMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<BaseInfoDTO> findOne(Long id) {
    log.debug("Request to get BaseInfo : {}", id);
    return baseInfoRepository.findById(id).map(baseInfoMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete BaseInfo : {}", id);
    baseInfoRepository.deleteById(id);
    baseInfoSearchRepository.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<BaseInfoDTO> search(String query, Pageable pageable) {
    log.debug("Request to search for a page of BaseInfos for query {}", query);
    return baseInfoSearchRepository.search(queryStringQuery(query), pageable).map(baseInfoMapper::toDto);
  }
}
