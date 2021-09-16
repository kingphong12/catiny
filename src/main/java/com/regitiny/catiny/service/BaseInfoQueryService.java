package com.regitiny.catiny.service;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*; // for static metamodels
import com.regitiny.catiny.domain.BaseInfo;
import com.regitiny.catiny.repository.BaseInfoRepository;
import com.regitiny.catiny.repository.search.BaseInfoSearchRepository;
import com.regitiny.catiny.service.criteria.BaseInfoCriteria;
import com.regitiny.catiny.service.dto.BaseInfoDTO;
import com.regitiny.catiny.service.mapper.BaseInfoMapper;
import java.util.List;
import javax.persistence.criteria.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link BaseInfo} entities in the database.
 * The main input is a {@link BaseInfoCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link BaseInfoDTO} or a {@link Page} of {@link BaseInfoDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
@GeneratedByJHipster
public class BaseInfoQueryService extends QueryService<BaseInfo> {

  private final Logger log = LoggerFactory.getLogger(BaseInfoQueryService.class);

  private final BaseInfoRepository baseInfoRepository;

  private final BaseInfoMapper baseInfoMapper;

  private final BaseInfoSearchRepository baseInfoSearchRepository;

  public BaseInfoQueryService(
    BaseInfoRepository baseInfoRepository,
    BaseInfoMapper baseInfoMapper,
    BaseInfoSearchRepository baseInfoSearchRepository
  ) {
    this.baseInfoRepository = baseInfoRepository;
    this.baseInfoMapper = baseInfoMapper;
    this.baseInfoSearchRepository = baseInfoSearchRepository;
  }

  /**
   * Return a {@link List} of {@link BaseInfoDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public List<BaseInfoDTO> findByCriteria(BaseInfoCriteria criteria) {
    log.debug("find by criteria : {}", criteria);
    final Specification<BaseInfo> specification = createSpecification(criteria);
    return baseInfoMapper.toDto(baseInfoRepository.findAll(specification));
  }

  /**
   * Return a {@link Page} of {@link BaseInfoDTO} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @param page The page, which should be returned.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public Page<BaseInfoDTO> findByCriteria(BaseInfoCriteria criteria, Pageable page) {
    log.debug("find by criteria : {}, page: {}", criteria, page);
    final Specification<BaseInfo> specification = createSpecification(criteria);
    return baseInfoRepository.findAll(specification, page).map(baseInfoMapper::toDto);
  }

  /**
   * Return the number of matching entities in the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the number of matching entities.
   */
  @Transactional(readOnly = true)
  public long countByCriteria(BaseInfoCriteria criteria) {
    log.debug("count by criteria : {}", criteria);
    final Specification<BaseInfo> specification = createSpecification(criteria);
    return baseInfoRepository.count(specification);
  }

  /**
   * Function to convert {@link BaseInfoCriteria} to a {@link Specification}
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching {@link Specification} of the entity.
   */
  protected Specification<BaseInfo> createSpecification(BaseInfoCriteria criteria) {
    Specification<BaseInfo> specification = Specification.where(null);
    if (criteria != null) {
      // This has to be called first, because the distinct method returns null
      if (criteria.getDistinct() != null) {
        specification = specification.and(distinct(criteria.getDistinct()));
      }
      if (criteria.getId() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getId(), BaseInfo_.id));
      }
      if (criteria.getUuid() != null) {
        specification = specification.and(buildSpecification(criteria.getUuid(), BaseInfo_.uuid));
      }
      if (criteria.getProcessStatus() != null) {
        specification = specification.and(buildSpecification(criteria.getProcessStatus(), BaseInfo_.processStatus));
      }
      if (criteria.getModifiedClass() != null) {
        specification = specification.and(buildStringSpecification(criteria.getModifiedClass(), BaseInfo_.modifiedClass));
      }
      if (criteria.getCreatedDate() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getCreatedDate(), BaseInfo_.createdDate));
      }
      if (criteria.getModifiedDate() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getModifiedDate(), BaseInfo_.modifiedDate));
      }
      if (criteria.getDeleted() != null) {
        specification = specification.and(buildSpecification(criteria.getDeleted(), BaseInfo_.deleted));
      }
      if (criteria.getPriorityIndex() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getPriorityIndex(), BaseInfo_.priorityIndex));
      }
      if (criteria.getCountUse() != null) {
        specification = specification.and(buildRangeSpecification(criteria.getCountUse(), BaseInfo_.countUse));
      }
      if (criteria.getHistoryId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getHistoryId(), root -> root.join(BaseInfo_.histories, JoinType.LEFT).get(HistoryUpdate_.id))
          );
      }
      if (criteria.getCreatedById() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getCreatedById(), root -> root.join(BaseInfo_.createdBy, JoinType.LEFT).get(MasterUser_.id))
          );
      }
      if (criteria.getModifiedById() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getModifiedById(), root -> root.join(BaseInfo_.modifiedBy, JoinType.LEFT).get(MasterUser_.id))
          );
      }
      if (criteria.getOwnerId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getOwnerId(), root -> root.join(BaseInfo_.owner, JoinType.LEFT).get(MasterUser_.id))
          );
      }
      if (criteria.getClassInfoId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getClassInfoId(), root -> root.join(BaseInfo_.classInfo, JoinType.LEFT).get(ClassInfo_.id))
          );
      }
      if (criteria.getPermissionId() != null) {
        specification =
          specification.and(
            buildSpecification(criteria.getPermissionId(), root -> root.join(BaseInfo_.permissions, JoinType.LEFT).get(Permission_.id))
          );
      }
    }
    return specification;
  }
}
