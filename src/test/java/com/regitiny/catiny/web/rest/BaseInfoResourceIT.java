package com.regitiny.catiny.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.IntegrationTest;
import com.regitiny.catiny.domain.BaseInfo;
import com.regitiny.catiny.domain.ClassInfo;
import com.regitiny.catiny.domain.HistoryUpdate;
import com.regitiny.catiny.domain.MasterUser;
import com.regitiny.catiny.domain.Permission;
import com.regitiny.catiny.domain.enumeration.ProcessStatus;
import com.regitiny.catiny.repository.BaseInfoRepository;
import com.regitiny.catiny.repository.search.BaseInfoSearchRepository;
import com.regitiny.catiny.service.criteria.BaseInfoCriteria;
import com.regitiny.catiny.service.dto.BaseInfoDTO;
import com.regitiny.catiny.service.mapper.BaseInfoMapper;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

/**
 * Integration tests for the {@link BaseInfoResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
@GeneratedByJHipster
class BaseInfoResourceIT {

  private static final UUID DEFAULT_UUID = UUID.randomUUID();
  private static final UUID UPDATED_UUID = UUID.randomUUID();

  private static final ProcessStatus DEFAULT_PROCESS_STATUS = ProcessStatus.NOT_PROCESSED;
  private static final ProcessStatus UPDATED_PROCESS_STATUS = ProcessStatus.PROCESSING;

  private static final String DEFAULT_MODIFIED_CLASS = "AAAAAAAAAA";
  private static final String UPDATED_MODIFIED_CLASS = "BBBBBBBBBB";

  private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
  private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

  private static final Instant DEFAULT_MODIFIED_DATE = Instant.ofEpochMilli(0L);
  private static final Instant UPDATED_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

  private static final String DEFAULT_NOTES = "AAAAAAAAAA";
  private static final String UPDATED_NOTES = "BBBBBBBBBB";

  private static final Boolean DEFAULT_DELETED = false;
  private static final Boolean UPDATED_DELETED = true;

  private static final Long DEFAULT_PRIORITY_INDEX = 1L;
  private static final Long UPDATED_PRIORITY_INDEX = 2L;
  private static final Long SMALLER_PRIORITY_INDEX = 1L - 1L;

  private static final Long DEFAULT_COUNT_USE = 1L;
  private static final Long UPDATED_COUNT_USE = 2L;
  private static final Long SMALLER_COUNT_USE = 1L - 1L;

  private static final String ENTITY_API_URL = "/api/base-infos";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
  private static final String ENTITY_SEARCH_API_URL = "/api/_search/base-infos";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private BaseInfoRepository baseInfoRepository;

  @Autowired
  private BaseInfoMapper baseInfoMapper;

  /**
   * This repository is mocked in the com.regitiny.catiny.repository.search test package.
   *
   * @see com.regitiny.catiny.repository.search.BaseInfoSearchRepositoryMockConfiguration
   */
  @Autowired
  private BaseInfoSearchRepository mockBaseInfoSearchRepository;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restBaseInfoMockMvc;

  private BaseInfo baseInfo;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static BaseInfo createEntity(EntityManager em) {
    BaseInfo baseInfo = new BaseInfo()
      .uuid(DEFAULT_UUID)
      .processStatus(DEFAULT_PROCESS_STATUS)
      .modifiedClass(DEFAULT_MODIFIED_CLASS)
      .createdDate(DEFAULT_CREATED_DATE)
      .modifiedDate(DEFAULT_MODIFIED_DATE)
      .notes(DEFAULT_NOTES)
      .deleted(DEFAULT_DELETED)
      .priorityIndex(DEFAULT_PRIORITY_INDEX)
      .countUse(DEFAULT_COUNT_USE);
    return baseInfo;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static BaseInfo createUpdatedEntity(EntityManager em) {
    BaseInfo baseInfo = new BaseInfo()
      .uuid(UPDATED_UUID)
      .processStatus(UPDATED_PROCESS_STATUS)
      .modifiedClass(UPDATED_MODIFIED_CLASS)
      .createdDate(UPDATED_CREATED_DATE)
      .modifiedDate(UPDATED_MODIFIED_DATE)
      .notes(UPDATED_NOTES)
      .deleted(UPDATED_DELETED)
      .priorityIndex(UPDATED_PRIORITY_INDEX)
      .countUse(UPDATED_COUNT_USE);
    return baseInfo;
  }

  @BeforeEach
  public void initTest() {
    baseInfo = createEntity(em);
  }

  @Test
  @Transactional
  void createBaseInfo() throws Exception {
    int databaseSizeBeforeCreate = baseInfoRepository.findAll().size();
    // Create the BaseInfo
    BaseInfoDTO baseInfoDTO = baseInfoMapper.toDto(baseInfo);
    restBaseInfoMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(baseInfoDTO)))
      .andExpect(status().isCreated());

    // Validate the BaseInfo in the database
    List<BaseInfo> baseInfoList = baseInfoRepository.findAll();
    assertThat(baseInfoList).hasSize(databaseSizeBeforeCreate + 1);
    BaseInfo testBaseInfo = baseInfoList.get(baseInfoList.size() - 1);
    assertThat(testBaseInfo.getUuid()).isEqualTo(DEFAULT_UUID);
    assertThat(testBaseInfo.getProcessStatus()).isEqualTo(DEFAULT_PROCESS_STATUS);
    assertThat(testBaseInfo.getModifiedClass()).isEqualTo(DEFAULT_MODIFIED_CLASS);
    assertThat(testBaseInfo.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
    assertThat(testBaseInfo.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    assertThat(testBaseInfo.getNotes()).isEqualTo(DEFAULT_NOTES);
    assertThat(testBaseInfo.getDeleted()).isEqualTo(DEFAULT_DELETED);
    assertThat(testBaseInfo.getPriorityIndex()).isEqualTo(DEFAULT_PRIORITY_INDEX);
    assertThat(testBaseInfo.getCountUse()).isEqualTo(DEFAULT_COUNT_USE);

    // Validate the BaseInfo in Elasticsearch
    verify(mockBaseInfoSearchRepository, times(1)).save(testBaseInfo);
  }

  @Test
  @Transactional
  void createBaseInfoWithExistingId() throws Exception {
    // Create the BaseInfo with an existing ID
    baseInfo.setId(1L);
    BaseInfoDTO baseInfoDTO = baseInfoMapper.toDto(baseInfo);

    int databaseSizeBeforeCreate = baseInfoRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restBaseInfoMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(baseInfoDTO)))
      .andExpect(status().isBadRequest());

    // Validate the BaseInfo in the database
    List<BaseInfo> baseInfoList = baseInfoRepository.findAll();
    assertThat(baseInfoList).hasSize(databaseSizeBeforeCreate);

    // Validate the BaseInfo in Elasticsearch
    verify(mockBaseInfoSearchRepository, times(0)).save(baseInfo);
  }

  @Test
  @Transactional
  void checkUuidIsRequired() throws Exception {
    int databaseSizeBeforeTest = baseInfoRepository.findAll().size();
    // set the field null
    baseInfo.setUuid(null);

    // Create the BaseInfo, which fails.
    BaseInfoDTO baseInfoDTO = baseInfoMapper.toDto(baseInfo);

    restBaseInfoMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(baseInfoDTO)))
      .andExpect(status().isBadRequest());

    List<BaseInfo> baseInfoList = baseInfoRepository.findAll();
    assertThat(baseInfoList).hasSize(databaseSizeBeforeTest);
  }

  @Test
  @Transactional
  void getAllBaseInfos() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList
    restBaseInfoMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(baseInfo.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].processStatus").value(hasItem(DEFAULT_PROCESS_STATUS.toString())))
      .andExpect(jsonPath("$.[*].modifiedClass").value(hasItem(DEFAULT_MODIFIED_CLASS)))
      .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
      .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE.toString())))
      .andExpect(jsonPath("$.[*].notes").value(hasItem(DEFAULT_NOTES.toString())))
      .andExpect(jsonPath("$.[*].deleted").value(hasItem(DEFAULT_DELETED.booleanValue())))
      .andExpect(jsonPath("$.[*].priorityIndex").value(hasItem(DEFAULT_PRIORITY_INDEX.intValue())))
      .andExpect(jsonPath("$.[*].countUse").value(hasItem(DEFAULT_COUNT_USE.intValue())));
  }

  @Test
  @Transactional
  void getBaseInfo() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get the baseInfo
    restBaseInfoMockMvc
      .perform(get(ENTITY_API_URL_ID, baseInfo.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(baseInfo.getId().intValue()))
      .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()))
      .andExpect(jsonPath("$.processStatus").value(DEFAULT_PROCESS_STATUS.toString()))
      .andExpect(jsonPath("$.modifiedClass").value(DEFAULT_MODIFIED_CLASS))
      .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
      .andExpect(jsonPath("$.modifiedDate").value(DEFAULT_MODIFIED_DATE.toString()))
      .andExpect(jsonPath("$.notes").value(DEFAULT_NOTES.toString()))
      .andExpect(jsonPath("$.deleted").value(DEFAULT_DELETED.booleanValue()))
      .andExpect(jsonPath("$.priorityIndex").value(DEFAULT_PRIORITY_INDEX.intValue()))
      .andExpect(jsonPath("$.countUse").value(DEFAULT_COUNT_USE.intValue()));
  }

  @Test
  @Transactional
  void getBaseInfosByIdFiltering() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    Long id = baseInfo.getId();

    defaultBaseInfoShouldBeFound("id.equals=" + id);
    defaultBaseInfoShouldNotBeFound("id.notEquals=" + id);

    defaultBaseInfoShouldBeFound("id.greaterThanOrEqual=" + id);
    defaultBaseInfoShouldNotBeFound("id.greaterThan=" + id);

    defaultBaseInfoShouldBeFound("id.lessThanOrEqual=" + id);
    defaultBaseInfoShouldNotBeFound("id.lessThan=" + id);
  }

  @Test
  @Transactional
  void getAllBaseInfosByUuidIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where uuid equals to DEFAULT_UUID
    defaultBaseInfoShouldBeFound("uuid.equals=" + DEFAULT_UUID);

    // Get all the baseInfoList where uuid equals to UPDATED_UUID
    defaultBaseInfoShouldNotBeFound("uuid.equals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllBaseInfosByUuidIsNotEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where uuid not equals to DEFAULT_UUID
    defaultBaseInfoShouldNotBeFound("uuid.notEquals=" + DEFAULT_UUID);

    // Get all the baseInfoList where uuid not equals to UPDATED_UUID
    defaultBaseInfoShouldBeFound("uuid.notEquals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllBaseInfosByUuidIsInShouldWork() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where uuid in DEFAULT_UUID or UPDATED_UUID
    defaultBaseInfoShouldBeFound("uuid.in=" + DEFAULT_UUID + "," + UPDATED_UUID);

    // Get all the baseInfoList where uuid equals to UPDATED_UUID
    defaultBaseInfoShouldNotBeFound("uuid.in=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllBaseInfosByUuidIsNullOrNotNull() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where uuid is not null
    defaultBaseInfoShouldBeFound("uuid.specified=true");

    // Get all the baseInfoList where uuid is null
    defaultBaseInfoShouldNotBeFound("uuid.specified=false");
  }

  @Test
  @Transactional
  void getAllBaseInfosByProcessStatusIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where processStatus equals to DEFAULT_PROCESS_STATUS
    defaultBaseInfoShouldBeFound("processStatus.equals=" + DEFAULT_PROCESS_STATUS);

    // Get all the baseInfoList where processStatus equals to UPDATED_PROCESS_STATUS
    defaultBaseInfoShouldNotBeFound("processStatus.equals=" + UPDATED_PROCESS_STATUS);
  }

  @Test
  @Transactional
  void getAllBaseInfosByProcessStatusIsNotEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where processStatus not equals to DEFAULT_PROCESS_STATUS
    defaultBaseInfoShouldNotBeFound("processStatus.notEquals=" + DEFAULT_PROCESS_STATUS);

    // Get all the baseInfoList where processStatus not equals to UPDATED_PROCESS_STATUS
    defaultBaseInfoShouldBeFound("processStatus.notEquals=" + UPDATED_PROCESS_STATUS);
  }

  @Test
  @Transactional
  void getAllBaseInfosByProcessStatusIsInShouldWork() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where processStatus in DEFAULT_PROCESS_STATUS or UPDATED_PROCESS_STATUS
    defaultBaseInfoShouldBeFound("processStatus.in=" + DEFAULT_PROCESS_STATUS + "," + UPDATED_PROCESS_STATUS);

    // Get all the baseInfoList where processStatus equals to UPDATED_PROCESS_STATUS
    defaultBaseInfoShouldNotBeFound("processStatus.in=" + UPDATED_PROCESS_STATUS);
  }

  @Test
  @Transactional
  void getAllBaseInfosByProcessStatusIsNullOrNotNull() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where processStatus is not null
    defaultBaseInfoShouldBeFound("processStatus.specified=true");

    // Get all the baseInfoList where processStatus is null
    defaultBaseInfoShouldNotBeFound("processStatus.specified=false");
  }

  @Test
  @Transactional
  void getAllBaseInfosByModifiedClassIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where modifiedClass equals to DEFAULT_MODIFIED_CLASS
    defaultBaseInfoShouldBeFound("modifiedClass.equals=" + DEFAULT_MODIFIED_CLASS);

    // Get all the baseInfoList where modifiedClass equals to UPDATED_MODIFIED_CLASS
    defaultBaseInfoShouldNotBeFound("modifiedClass.equals=" + UPDATED_MODIFIED_CLASS);
  }

  @Test
  @Transactional
  void getAllBaseInfosByModifiedClassIsNotEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where modifiedClass not equals to DEFAULT_MODIFIED_CLASS
    defaultBaseInfoShouldNotBeFound("modifiedClass.notEquals=" + DEFAULT_MODIFIED_CLASS);

    // Get all the baseInfoList where modifiedClass not equals to UPDATED_MODIFIED_CLASS
    defaultBaseInfoShouldBeFound("modifiedClass.notEquals=" + UPDATED_MODIFIED_CLASS);
  }

  @Test
  @Transactional
  void getAllBaseInfosByModifiedClassIsInShouldWork() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where modifiedClass in DEFAULT_MODIFIED_CLASS or UPDATED_MODIFIED_CLASS
    defaultBaseInfoShouldBeFound("modifiedClass.in=" + DEFAULT_MODIFIED_CLASS + "," + UPDATED_MODIFIED_CLASS);

    // Get all the baseInfoList where modifiedClass equals to UPDATED_MODIFIED_CLASS
    defaultBaseInfoShouldNotBeFound("modifiedClass.in=" + UPDATED_MODIFIED_CLASS);
  }

  @Test
  @Transactional
  void getAllBaseInfosByModifiedClassIsNullOrNotNull() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where modifiedClass is not null
    defaultBaseInfoShouldBeFound("modifiedClass.specified=true");

    // Get all the baseInfoList where modifiedClass is null
    defaultBaseInfoShouldNotBeFound("modifiedClass.specified=false");
  }

  @Test
  @Transactional
  void getAllBaseInfosByModifiedClassContainsSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where modifiedClass contains DEFAULT_MODIFIED_CLASS
    defaultBaseInfoShouldBeFound("modifiedClass.contains=" + DEFAULT_MODIFIED_CLASS);

    // Get all the baseInfoList where modifiedClass contains UPDATED_MODIFIED_CLASS
    defaultBaseInfoShouldNotBeFound("modifiedClass.contains=" + UPDATED_MODIFIED_CLASS);
  }

  @Test
  @Transactional
  void getAllBaseInfosByModifiedClassNotContainsSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where modifiedClass does not contain DEFAULT_MODIFIED_CLASS
    defaultBaseInfoShouldNotBeFound("modifiedClass.doesNotContain=" + DEFAULT_MODIFIED_CLASS);

    // Get all the baseInfoList where modifiedClass does not contain UPDATED_MODIFIED_CLASS
    defaultBaseInfoShouldBeFound("modifiedClass.doesNotContain=" + UPDATED_MODIFIED_CLASS);
  }

  @Test
  @Transactional
  void getAllBaseInfosByCreatedDateIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where createdDate equals to DEFAULT_CREATED_DATE
    defaultBaseInfoShouldBeFound("createdDate.equals=" + DEFAULT_CREATED_DATE);

    // Get all the baseInfoList where createdDate equals to UPDATED_CREATED_DATE
    defaultBaseInfoShouldNotBeFound("createdDate.equals=" + UPDATED_CREATED_DATE);
  }

  @Test
  @Transactional
  void getAllBaseInfosByCreatedDateIsNotEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where createdDate not equals to DEFAULT_CREATED_DATE
    defaultBaseInfoShouldNotBeFound("createdDate.notEquals=" + DEFAULT_CREATED_DATE);

    // Get all the baseInfoList where createdDate not equals to UPDATED_CREATED_DATE
    defaultBaseInfoShouldBeFound("createdDate.notEquals=" + UPDATED_CREATED_DATE);
  }

  @Test
  @Transactional
  void getAllBaseInfosByCreatedDateIsInShouldWork() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where createdDate in DEFAULT_CREATED_DATE or UPDATED_CREATED_DATE
    defaultBaseInfoShouldBeFound("createdDate.in=" + DEFAULT_CREATED_DATE + "," + UPDATED_CREATED_DATE);

    // Get all the baseInfoList where createdDate equals to UPDATED_CREATED_DATE
    defaultBaseInfoShouldNotBeFound("createdDate.in=" + UPDATED_CREATED_DATE);
  }

  @Test
  @Transactional
  void getAllBaseInfosByCreatedDateIsNullOrNotNull() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where createdDate is not null
    defaultBaseInfoShouldBeFound("createdDate.specified=true");

    // Get all the baseInfoList where createdDate is null
    defaultBaseInfoShouldNotBeFound("createdDate.specified=false");
  }

  @Test
  @Transactional
  void getAllBaseInfosByModifiedDateIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where modifiedDate equals to DEFAULT_MODIFIED_DATE
    defaultBaseInfoShouldBeFound("modifiedDate.equals=" + DEFAULT_MODIFIED_DATE);

    // Get all the baseInfoList where modifiedDate equals to UPDATED_MODIFIED_DATE
    defaultBaseInfoShouldNotBeFound("modifiedDate.equals=" + UPDATED_MODIFIED_DATE);
  }

  @Test
  @Transactional
  void getAllBaseInfosByModifiedDateIsNotEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where modifiedDate not equals to DEFAULT_MODIFIED_DATE
    defaultBaseInfoShouldNotBeFound("modifiedDate.notEquals=" + DEFAULT_MODIFIED_DATE);

    // Get all the baseInfoList where modifiedDate not equals to UPDATED_MODIFIED_DATE
    defaultBaseInfoShouldBeFound("modifiedDate.notEquals=" + UPDATED_MODIFIED_DATE);
  }

  @Test
  @Transactional
  void getAllBaseInfosByModifiedDateIsInShouldWork() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where modifiedDate in DEFAULT_MODIFIED_DATE or UPDATED_MODIFIED_DATE
    defaultBaseInfoShouldBeFound("modifiedDate.in=" + DEFAULT_MODIFIED_DATE + "," + UPDATED_MODIFIED_DATE);

    // Get all the baseInfoList where modifiedDate equals to UPDATED_MODIFIED_DATE
    defaultBaseInfoShouldNotBeFound("modifiedDate.in=" + UPDATED_MODIFIED_DATE);
  }

  @Test
  @Transactional
  void getAllBaseInfosByModifiedDateIsNullOrNotNull() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where modifiedDate is not null
    defaultBaseInfoShouldBeFound("modifiedDate.specified=true");

    // Get all the baseInfoList where modifiedDate is null
    defaultBaseInfoShouldNotBeFound("modifiedDate.specified=false");
  }

  @Test
  @Transactional
  void getAllBaseInfosByDeletedIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where deleted equals to DEFAULT_DELETED
    defaultBaseInfoShouldBeFound("deleted.equals=" + DEFAULT_DELETED);

    // Get all the baseInfoList where deleted equals to UPDATED_DELETED
    defaultBaseInfoShouldNotBeFound("deleted.equals=" + UPDATED_DELETED);
  }

  @Test
  @Transactional
  void getAllBaseInfosByDeletedIsNotEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where deleted not equals to DEFAULT_DELETED
    defaultBaseInfoShouldNotBeFound("deleted.notEquals=" + DEFAULT_DELETED);

    // Get all the baseInfoList where deleted not equals to UPDATED_DELETED
    defaultBaseInfoShouldBeFound("deleted.notEquals=" + UPDATED_DELETED);
  }

  @Test
  @Transactional
  void getAllBaseInfosByDeletedIsInShouldWork() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where deleted in DEFAULT_DELETED or UPDATED_DELETED
    defaultBaseInfoShouldBeFound("deleted.in=" + DEFAULT_DELETED + "," + UPDATED_DELETED);

    // Get all the baseInfoList where deleted equals to UPDATED_DELETED
    defaultBaseInfoShouldNotBeFound("deleted.in=" + UPDATED_DELETED);
  }

  @Test
  @Transactional
  void getAllBaseInfosByDeletedIsNullOrNotNull() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where deleted is not null
    defaultBaseInfoShouldBeFound("deleted.specified=true");

    // Get all the baseInfoList where deleted is null
    defaultBaseInfoShouldNotBeFound("deleted.specified=false");
  }

  @Test
  @Transactional
  void getAllBaseInfosByPriorityIndexIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where priorityIndex equals to DEFAULT_PRIORITY_INDEX
    defaultBaseInfoShouldBeFound("priorityIndex.equals=" + DEFAULT_PRIORITY_INDEX);

    // Get all the baseInfoList where priorityIndex equals to UPDATED_PRIORITY_INDEX
    defaultBaseInfoShouldNotBeFound("priorityIndex.equals=" + UPDATED_PRIORITY_INDEX);
  }

  @Test
  @Transactional
  void getAllBaseInfosByPriorityIndexIsNotEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where priorityIndex not equals to DEFAULT_PRIORITY_INDEX
    defaultBaseInfoShouldNotBeFound("priorityIndex.notEquals=" + DEFAULT_PRIORITY_INDEX);

    // Get all the baseInfoList where priorityIndex not equals to UPDATED_PRIORITY_INDEX
    defaultBaseInfoShouldBeFound("priorityIndex.notEquals=" + UPDATED_PRIORITY_INDEX);
  }

  @Test
  @Transactional
  void getAllBaseInfosByPriorityIndexIsInShouldWork() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where priorityIndex in DEFAULT_PRIORITY_INDEX or UPDATED_PRIORITY_INDEX
    defaultBaseInfoShouldBeFound("priorityIndex.in=" + DEFAULT_PRIORITY_INDEX + "," + UPDATED_PRIORITY_INDEX);

    // Get all the baseInfoList where priorityIndex equals to UPDATED_PRIORITY_INDEX
    defaultBaseInfoShouldNotBeFound("priorityIndex.in=" + UPDATED_PRIORITY_INDEX);
  }

  @Test
  @Transactional
  void getAllBaseInfosByPriorityIndexIsNullOrNotNull() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where priorityIndex is not null
    defaultBaseInfoShouldBeFound("priorityIndex.specified=true");

    // Get all the baseInfoList where priorityIndex is null
    defaultBaseInfoShouldNotBeFound("priorityIndex.specified=false");
  }

  @Test
  @Transactional
  void getAllBaseInfosByPriorityIndexIsGreaterThanOrEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where priorityIndex is greater than or equal to DEFAULT_PRIORITY_INDEX
    defaultBaseInfoShouldBeFound("priorityIndex.greaterThanOrEqual=" + DEFAULT_PRIORITY_INDEX);

    // Get all the baseInfoList where priorityIndex is greater than or equal to UPDATED_PRIORITY_INDEX
    defaultBaseInfoShouldNotBeFound("priorityIndex.greaterThanOrEqual=" + UPDATED_PRIORITY_INDEX);
  }

  @Test
  @Transactional
  void getAllBaseInfosByPriorityIndexIsLessThanOrEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where priorityIndex is less than or equal to DEFAULT_PRIORITY_INDEX
    defaultBaseInfoShouldBeFound("priorityIndex.lessThanOrEqual=" + DEFAULT_PRIORITY_INDEX);

    // Get all the baseInfoList where priorityIndex is less than or equal to SMALLER_PRIORITY_INDEX
    defaultBaseInfoShouldNotBeFound("priorityIndex.lessThanOrEqual=" + SMALLER_PRIORITY_INDEX);
  }

  @Test
  @Transactional
  void getAllBaseInfosByPriorityIndexIsLessThanSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where priorityIndex is less than DEFAULT_PRIORITY_INDEX
    defaultBaseInfoShouldNotBeFound("priorityIndex.lessThan=" + DEFAULT_PRIORITY_INDEX);

    // Get all the baseInfoList where priorityIndex is less than UPDATED_PRIORITY_INDEX
    defaultBaseInfoShouldBeFound("priorityIndex.lessThan=" + UPDATED_PRIORITY_INDEX);
  }

  @Test
  @Transactional
  void getAllBaseInfosByPriorityIndexIsGreaterThanSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where priorityIndex is greater than DEFAULT_PRIORITY_INDEX
    defaultBaseInfoShouldNotBeFound("priorityIndex.greaterThan=" + DEFAULT_PRIORITY_INDEX);

    // Get all the baseInfoList where priorityIndex is greater than SMALLER_PRIORITY_INDEX
    defaultBaseInfoShouldBeFound("priorityIndex.greaterThan=" + SMALLER_PRIORITY_INDEX);
  }

  @Test
  @Transactional
  void getAllBaseInfosByCountUseIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where countUse equals to DEFAULT_COUNT_USE
    defaultBaseInfoShouldBeFound("countUse.equals=" + DEFAULT_COUNT_USE);

    // Get all the baseInfoList where countUse equals to UPDATED_COUNT_USE
    defaultBaseInfoShouldNotBeFound("countUse.equals=" + UPDATED_COUNT_USE);
  }

  @Test
  @Transactional
  void getAllBaseInfosByCountUseIsNotEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where countUse not equals to DEFAULT_COUNT_USE
    defaultBaseInfoShouldNotBeFound("countUse.notEquals=" + DEFAULT_COUNT_USE);

    // Get all the baseInfoList where countUse not equals to UPDATED_COUNT_USE
    defaultBaseInfoShouldBeFound("countUse.notEquals=" + UPDATED_COUNT_USE);
  }

  @Test
  @Transactional
  void getAllBaseInfosByCountUseIsInShouldWork() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where countUse in DEFAULT_COUNT_USE or UPDATED_COUNT_USE
    defaultBaseInfoShouldBeFound("countUse.in=" + DEFAULT_COUNT_USE + "," + UPDATED_COUNT_USE);

    // Get all the baseInfoList where countUse equals to UPDATED_COUNT_USE
    defaultBaseInfoShouldNotBeFound("countUse.in=" + UPDATED_COUNT_USE);
  }

  @Test
  @Transactional
  void getAllBaseInfosByCountUseIsNullOrNotNull() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where countUse is not null
    defaultBaseInfoShouldBeFound("countUse.specified=true");

    // Get all the baseInfoList where countUse is null
    defaultBaseInfoShouldNotBeFound("countUse.specified=false");
  }

  @Test
  @Transactional
  void getAllBaseInfosByCountUseIsGreaterThanOrEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where countUse is greater than or equal to DEFAULT_COUNT_USE
    defaultBaseInfoShouldBeFound("countUse.greaterThanOrEqual=" + DEFAULT_COUNT_USE);

    // Get all the baseInfoList where countUse is greater than or equal to UPDATED_COUNT_USE
    defaultBaseInfoShouldNotBeFound("countUse.greaterThanOrEqual=" + UPDATED_COUNT_USE);
  }

  @Test
  @Transactional
  void getAllBaseInfosByCountUseIsLessThanOrEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where countUse is less than or equal to DEFAULT_COUNT_USE
    defaultBaseInfoShouldBeFound("countUse.lessThanOrEqual=" + DEFAULT_COUNT_USE);

    // Get all the baseInfoList where countUse is less than or equal to SMALLER_COUNT_USE
    defaultBaseInfoShouldNotBeFound("countUse.lessThanOrEqual=" + SMALLER_COUNT_USE);
  }

  @Test
  @Transactional
  void getAllBaseInfosByCountUseIsLessThanSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where countUse is less than DEFAULT_COUNT_USE
    defaultBaseInfoShouldNotBeFound("countUse.lessThan=" + DEFAULT_COUNT_USE);

    // Get all the baseInfoList where countUse is less than UPDATED_COUNT_USE
    defaultBaseInfoShouldBeFound("countUse.lessThan=" + UPDATED_COUNT_USE);
  }

  @Test
  @Transactional
  void getAllBaseInfosByCountUseIsGreaterThanSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    // Get all the baseInfoList where countUse is greater than DEFAULT_COUNT_USE
    defaultBaseInfoShouldNotBeFound("countUse.greaterThan=" + DEFAULT_COUNT_USE);

    // Get all the baseInfoList where countUse is greater than SMALLER_COUNT_USE
    defaultBaseInfoShouldBeFound("countUse.greaterThan=" + SMALLER_COUNT_USE);
  }

  @Test
  @Transactional
  void getAllBaseInfosByHistoryIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);
    HistoryUpdate history;
    if (TestUtil.findAll(em, HistoryUpdate.class).isEmpty()) {
      history = HistoryUpdateResourceIT.createEntity(em);
      em.persist(history);
      em.flush();
    } else {
      history = TestUtil.findAll(em, HistoryUpdate.class).get(0);
    }
    em.persist(history);
    em.flush();
    baseInfo.addHistory(history);
    baseInfoRepository.saveAndFlush(baseInfo);
    Long historyId = history.getId();

    // Get all the baseInfoList where history equals to historyId
    defaultBaseInfoShouldBeFound("historyId.equals=" + historyId);

    // Get all the baseInfoList where history equals to (historyId + 1)
    defaultBaseInfoShouldNotBeFound("historyId.equals=" + (historyId + 1));
  }

  @Test
  @Transactional
  void getAllBaseInfosByCreatedByIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);
    MasterUser createdBy;
    if (TestUtil.findAll(em, MasterUser.class).isEmpty()) {
      createdBy = MasterUserResourceIT.createEntity(em);
      em.persist(createdBy);
      em.flush();
    } else {
      createdBy = TestUtil.findAll(em, MasterUser.class).get(0);
    }
    em.persist(createdBy);
    em.flush();
    baseInfo.setCreatedBy(createdBy);
    baseInfoRepository.saveAndFlush(baseInfo);
    Long createdById = createdBy.getId();

    // Get all the baseInfoList where createdBy equals to createdById
    defaultBaseInfoShouldBeFound("createdById.equals=" + createdById);

    // Get all the baseInfoList where createdBy equals to (createdById + 1)
    defaultBaseInfoShouldNotBeFound("createdById.equals=" + (createdById + 1));
  }

  @Test
  @Transactional
  void getAllBaseInfosByModifiedByIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);
    MasterUser modifiedBy;
    if (TestUtil.findAll(em, MasterUser.class).isEmpty()) {
      modifiedBy = MasterUserResourceIT.createEntity(em);
      em.persist(modifiedBy);
      em.flush();
    } else {
      modifiedBy = TestUtil.findAll(em, MasterUser.class).get(0);
    }
    em.persist(modifiedBy);
    em.flush();
    baseInfo.setModifiedBy(modifiedBy);
    baseInfoRepository.saveAndFlush(baseInfo);
    Long modifiedById = modifiedBy.getId();

    // Get all the baseInfoList where modifiedBy equals to modifiedById
    defaultBaseInfoShouldBeFound("modifiedById.equals=" + modifiedById);

    // Get all the baseInfoList where modifiedBy equals to (modifiedById + 1)
    defaultBaseInfoShouldNotBeFound("modifiedById.equals=" + (modifiedById + 1));
  }

  @Test
  @Transactional
  void getAllBaseInfosByOwnerIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);
    MasterUser owner;
    if (TestUtil.findAll(em, MasterUser.class).isEmpty()) {
      owner = MasterUserResourceIT.createEntity(em);
      em.persist(owner);
      em.flush();
    } else {
      owner = TestUtil.findAll(em, MasterUser.class).get(0);
    }
    em.persist(owner);
    em.flush();
    baseInfo.setOwner(owner);
    baseInfoRepository.saveAndFlush(baseInfo);
    Long ownerId = owner.getId();

    // Get all the baseInfoList where owner equals to ownerId
    defaultBaseInfoShouldBeFound("ownerId.equals=" + ownerId);

    // Get all the baseInfoList where owner equals to (ownerId + 1)
    defaultBaseInfoShouldNotBeFound("ownerId.equals=" + (ownerId + 1));
  }

  @Test
  @Transactional
  void getAllBaseInfosByClassInfoIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);
    ClassInfo classInfo;
    if (TestUtil.findAll(em, ClassInfo.class).isEmpty()) {
      classInfo = ClassInfoResourceIT.createEntity(em);
      em.persist(classInfo);
      em.flush();
    } else {
      classInfo = TestUtil.findAll(em, ClassInfo.class).get(0);
    }
    em.persist(classInfo);
    em.flush();
    baseInfo.setClassInfo(classInfo);
    baseInfoRepository.saveAndFlush(baseInfo);
    Long classInfoId = classInfo.getId();

    // Get all the baseInfoList where classInfo equals to classInfoId
    defaultBaseInfoShouldBeFound("classInfoId.equals=" + classInfoId);

    // Get all the baseInfoList where classInfo equals to (classInfoId + 1)
    defaultBaseInfoShouldNotBeFound("classInfoId.equals=" + (classInfoId + 1));
  }

  @Test
  @Transactional
  void getAllBaseInfosByPermissionIsEqualToSomething() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);
    Permission permission;
    if (TestUtil.findAll(em, Permission.class).isEmpty()) {
      permission = PermissionResourceIT.createEntity(em);
      em.persist(permission);
      em.flush();
    } else {
      permission = TestUtil.findAll(em, Permission.class).get(0);
    }
    em.persist(permission);
    em.flush();
    baseInfo.addPermission(permission);
    baseInfoRepository.saveAndFlush(baseInfo);
    Long permissionId = permission.getId();

    // Get all the baseInfoList where permission equals to permissionId
    defaultBaseInfoShouldBeFound("permissionId.equals=" + permissionId);

    // Get all the baseInfoList where permission equals to (permissionId + 1)
    defaultBaseInfoShouldNotBeFound("permissionId.equals=" + (permissionId + 1));
  }

  /**
   * Executes the search, and checks that the default entity is returned.
   */
  private void defaultBaseInfoShouldBeFound(String filter) throws Exception {
    restBaseInfoMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(baseInfo.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].processStatus").value(hasItem(DEFAULT_PROCESS_STATUS.toString())))
      .andExpect(jsonPath("$.[*].modifiedClass").value(hasItem(DEFAULT_MODIFIED_CLASS)))
      .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
      .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE.toString())))
      .andExpect(jsonPath("$.[*].notes").value(hasItem(DEFAULT_NOTES.toString())))
      .andExpect(jsonPath("$.[*].deleted").value(hasItem(DEFAULT_DELETED.booleanValue())))
      .andExpect(jsonPath("$.[*].priorityIndex").value(hasItem(DEFAULT_PRIORITY_INDEX.intValue())))
      .andExpect(jsonPath("$.[*].countUse").value(hasItem(DEFAULT_COUNT_USE.intValue())));

    // Check, that the count call also returns 1
    restBaseInfoMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("1"));
  }

  /**
   * Executes the search, and checks that the default entity is not returned.
   */
  private void defaultBaseInfoShouldNotBeFound(String filter) throws Exception {
    restBaseInfoMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$").isArray())
      .andExpect(jsonPath("$").isEmpty());

    // Check, that the count call also returns 0
    restBaseInfoMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("0"));
  }

  @Test
  @Transactional
  void getNonExistingBaseInfo() throws Exception {
    // Get the baseInfo
    restBaseInfoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewBaseInfo() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    int databaseSizeBeforeUpdate = baseInfoRepository.findAll().size();

    // Update the baseInfo
    BaseInfo updatedBaseInfo = baseInfoRepository.findById(baseInfo.getId()).get();
    // Disconnect from session so that the updates on updatedBaseInfo are not directly saved in db
    em.detach(updatedBaseInfo);
    updatedBaseInfo
      .uuid(UPDATED_UUID)
      .processStatus(UPDATED_PROCESS_STATUS)
      .modifiedClass(UPDATED_MODIFIED_CLASS)
      .createdDate(UPDATED_CREATED_DATE)
      .modifiedDate(UPDATED_MODIFIED_DATE)
      .notes(UPDATED_NOTES)
      .deleted(UPDATED_DELETED)
      .priorityIndex(UPDATED_PRIORITY_INDEX)
      .countUse(UPDATED_COUNT_USE);
    BaseInfoDTO baseInfoDTO = baseInfoMapper.toDto(updatedBaseInfo);

    restBaseInfoMockMvc
      .perform(
        put(ENTITY_API_URL_ID, baseInfoDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(baseInfoDTO))
      )
      .andExpect(status().isOk());

    // Validate the BaseInfo in the database
    List<BaseInfo> baseInfoList = baseInfoRepository.findAll();
    assertThat(baseInfoList).hasSize(databaseSizeBeforeUpdate);
    BaseInfo testBaseInfo = baseInfoList.get(baseInfoList.size() - 1);
    assertThat(testBaseInfo.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testBaseInfo.getProcessStatus()).isEqualTo(UPDATED_PROCESS_STATUS);
    assertThat(testBaseInfo.getModifiedClass()).isEqualTo(UPDATED_MODIFIED_CLASS);
    assertThat(testBaseInfo.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
    assertThat(testBaseInfo.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    assertThat(testBaseInfo.getNotes()).isEqualTo(UPDATED_NOTES);
    assertThat(testBaseInfo.getDeleted()).isEqualTo(UPDATED_DELETED);
    assertThat(testBaseInfo.getPriorityIndex()).isEqualTo(UPDATED_PRIORITY_INDEX);
    assertThat(testBaseInfo.getCountUse()).isEqualTo(UPDATED_COUNT_USE);

    // Validate the BaseInfo in Elasticsearch
    verify(mockBaseInfoSearchRepository).save(testBaseInfo);
  }

  @Test
  @Transactional
  void putNonExistingBaseInfo() throws Exception {
    int databaseSizeBeforeUpdate = baseInfoRepository.findAll().size();
    baseInfo.setId(count.incrementAndGet());

    // Create the BaseInfo
    BaseInfoDTO baseInfoDTO = baseInfoMapper.toDto(baseInfo);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restBaseInfoMockMvc
      .perform(
        put(ENTITY_API_URL_ID, baseInfoDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(baseInfoDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the BaseInfo in the database
    List<BaseInfo> baseInfoList = baseInfoRepository.findAll();
    assertThat(baseInfoList).hasSize(databaseSizeBeforeUpdate);

    // Validate the BaseInfo in Elasticsearch
    verify(mockBaseInfoSearchRepository, times(0)).save(baseInfo);
  }

  @Test
  @Transactional
  void putWithIdMismatchBaseInfo() throws Exception {
    int databaseSizeBeforeUpdate = baseInfoRepository.findAll().size();
    baseInfo.setId(count.incrementAndGet());

    // Create the BaseInfo
    BaseInfoDTO baseInfoDTO = baseInfoMapper.toDto(baseInfo);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restBaseInfoMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(baseInfoDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the BaseInfo in the database
    List<BaseInfo> baseInfoList = baseInfoRepository.findAll();
    assertThat(baseInfoList).hasSize(databaseSizeBeforeUpdate);

    // Validate the BaseInfo in Elasticsearch
    verify(mockBaseInfoSearchRepository, times(0)).save(baseInfo);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamBaseInfo() throws Exception {
    int databaseSizeBeforeUpdate = baseInfoRepository.findAll().size();
    baseInfo.setId(count.incrementAndGet());

    // Create the BaseInfo
    BaseInfoDTO baseInfoDTO = baseInfoMapper.toDto(baseInfo);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restBaseInfoMockMvc
      .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(baseInfoDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the BaseInfo in the database
    List<BaseInfo> baseInfoList = baseInfoRepository.findAll();
    assertThat(baseInfoList).hasSize(databaseSizeBeforeUpdate);

    // Validate the BaseInfo in Elasticsearch
    verify(mockBaseInfoSearchRepository, times(0)).save(baseInfo);
  }

  @Test
  @Transactional
  void partialUpdateBaseInfoWithPatch() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    int databaseSizeBeforeUpdate = baseInfoRepository.findAll().size();

    // Update the baseInfo using partial update
    BaseInfo partialUpdatedBaseInfo = new BaseInfo();
    partialUpdatedBaseInfo.setId(baseInfo.getId());

    partialUpdatedBaseInfo
      .modifiedClass(UPDATED_MODIFIED_CLASS)
      .createdDate(UPDATED_CREATED_DATE)
      .modifiedDate(UPDATED_MODIFIED_DATE)
      .notes(UPDATED_NOTES)
      .priorityIndex(UPDATED_PRIORITY_INDEX)
      .countUse(UPDATED_COUNT_USE);

    restBaseInfoMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedBaseInfo.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBaseInfo))
      )
      .andExpect(status().isOk());

    // Validate the BaseInfo in the database
    List<BaseInfo> baseInfoList = baseInfoRepository.findAll();
    assertThat(baseInfoList).hasSize(databaseSizeBeforeUpdate);
    BaseInfo testBaseInfo = baseInfoList.get(baseInfoList.size() - 1);
    assertThat(testBaseInfo.getUuid()).isEqualTo(DEFAULT_UUID);
    assertThat(testBaseInfo.getProcessStatus()).isEqualTo(DEFAULT_PROCESS_STATUS);
    assertThat(testBaseInfo.getModifiedClass()).isEqualTo(UPDATED_MODIFIED_CLASS);
    assertThat(testBaseInfo.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
    assertThat(testBaseInfo.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    assertThat(testBaseInfo.getNotes()).isEqualTo(UPDATED_NOTES);
    assertThat(testBaseInfo.getDeleted()).isEqualTo(DEFAULT_DELETED);
    assertThat(testBaseInfo.getPriorityIndex()).isEqualTo(UPDATED_PRIORITY_INDEX);
    assertThat(testBaseInfo.getCountUse()).isEqualTo(UPDATED_COUNT_USE);
  }

  @Test
  @Transactional
  void fullUpdateBaseInfoWithPatch() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    int databaseSizeBeforeUpdate = baseInfoRepository.findAll().size();

    // Update the baseInfo using partial update
    BaseInfo partialUpdatedBaseInfo = new BaseInfo();
    partialUpdatedBaseInfo.setId(baseInfo.getId());

    partialUpdatedBaseInfo
      .uuid(UPDATED_UUID)
      .processStatus(UPDATED_PROCESS_STATUS)
      .modifiedClass(UPDATED_MODIFIED_CLASS)
      .createdDate(UPDATED_CREATED_DATE)
      .modifiedDate(UPDATED_MODIFIED_DATE)
      .notes(UPDATED_NOTES)
      .deleted(UPDATED_DELETED)
      .priorityIndex(UPDATED_PRIORITY_INDEX)
      .countUse(UPDATED_COUNT_USE);

    restBaseInfoMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedBaseInfo.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBaseInfo))
      )
      .andExpect(status().isOk());

    // Validate the BaseInfo in the database
    List<BaseInfo> baseInfoList = baseInfoRepository.findAll();
    assertThat(baseInfoList).hasSize(databaseSizeBeforeUpdate);
    BaseInfo testBaseInfo = baseInfoList.get(baseInfoList.size() - 1);
    assertThat(testBaseInfo.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testBaseInfo.getProcessStatus()).isEqualTo(UPDATED_PROCESS_STATUS);
    assertThat(testBaseInfo.getModifiedClass()).isEqualTo(UPDATED_MODIFIED_CLASS);
    assertThat(testBaseInfo.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
    assertThat(testBaseInfo.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    assertThat(testBaseInfo.getNotes()).isEqualTo(UPDATED_NOTES);
    assertThat(testBaseInfo.getDeleted()).isEqualTo(UPDATED_DELETED);
    assertThat(testBaseInfo.getPriorityIndex()).isEqualTo(UPDATED_PRIORITY_INDEX);
    assertThat(testBaseInfo.getCountUse()).isEqualTo(UPDATED_COUNT_USE);
  }

  @Test
  @Transactional
  void patchNonExistingBaseInfo() throws Exception {
    int databaseSizeBeforeUpdate = baseInfoRepository.findAll().size();
    baseInfo.setId(count.incrementAndGet());

    // Create the BaseInfo
    BaseInfoDTO baseInfoDTO = baseInfoMapper.toDto(baseInfo);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restBaseInfoMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, baseInfoDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(baseInfoDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the BaseInfo in the database
    List<BaseInfo> baseInfoList = baseInfoRepository.findAll();
    assertThat(baseInfoList).hasSize(databaseSizeBeforeUpdate);

    // Validate the BaseInfo in Elasticsearch
    verify(mockBaseInfoSearchRepository, times(0)).save(baseInfo);
  }

  @Test
  @Transactional
  void patchWithIdMismatchBaseInfo() throws Exception {
    int databaseSizeBeforeUpdate = baseInfoRepository.findAll().size();
    baseInfo.setId(count.incrementAndGet());

    // Create the BaseInfo
    BaseInfoDTO baseInfoDTO = baseInfoMapper.toDto(baseInfo);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restBaseInfoMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(baseInfoDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the BaseInfo in the database
    List<BaseInfo> baseInfoList = baseInfoRepository.findAll();
    assertThat(baseInfoList).hasSize(databaseSizeBeforeUpdate);

    // Validate the BaseInfo in Elasticsearch
    verify(mockBaseInfoSearchRepository, times(0)).save(baseInfo);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamBaseInfo() throws Exception {
    int databaseSizeBeforeUpdate = baseInfoRepository.findAll().size();
    baseInfo.setId(count.incrementAndGet());

    // Create the BaseInfo
    BaseInfoDTO baseInfoDTO = baseInfoMapper.toDto(baseInfo);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restBaseInfoMockMvc
      .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(baseInfoDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the BaseInfo in the database
    List<BaseInfo> baseInfoList = baseInfoRepository.findAll();
    assertThat(baseInfoList).hasSize(databaseSizeBeforeUpdate);

    // Validate the BaseInfo in Elasticsearch
    verify(mockBaseInfoSearchRepository, times(0)).save(baseInfo);
  }

  @Test
  @Transactional
  void deleteBaseInfo() throws Exception {
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);

    int databaseSizeBeforeDelete = baseInfoRepository.findAll().size();

    // Delete the baseInfo
    restBaseInfoMockMvc
      .perform(delete(ENTITY_API_URL_ID, baseInfo.getId()).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<BaseInfo> baseInfoList = baseInfoRepository.findAll();
    assertThat(baseInfoList).hasSize(databaseSizeBeforeDelete - 1);

    // Validate the BaseInfo in Elasticsearch
    verify(mockBaseInfoSearchRepository, times(1)).deleteById(baseInfo.getId());
  }

  @Test
  @Transactional
  void searchBaseInfo() throws Exception {
    // Configure the mock search repository
    // Initialize the database
    baseInfoRepository.saveAndFlush(baseInfo);
    when(mockBaseInfoSearchRepository.search("id:" + baseInfo.getId(), PageRequest.of(0, 20)))
      .thenReturn(new PageImpl<>(Collections.singletonList(baseInfo), PageRequest.of(0, 1), 1));

    // Search the baseInfo
    restBaseInfoMockMvc
      .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + baseInfo.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(baseInfo.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].processStatus").value(hasItem(DEFAULT_PROCESS_STATUS.toString())))
      .andExpect(jsonPath("$.[*].modifiedClass").value(hasItem(DEFAULT_MODIFIED_CLASS)))
      .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
      .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE.toString())))
      .andExpect(jsonPath("$.[*].notes").value(hasItem(DEFAULT_NOTES.toString())))
      .andExpect(jsonPath("$.[*].deleted").value(hasItem(DEFAULT_DELETED.booleanValue())))
      .andExpect(jsonPath("$.[*].priorityIndex").value(hasItem(DEFAULT_PRIORITY_INDEX.intValue())))
      .andExpect(jsonPath("$.[*].countUse").value(hasItem(DEFAULT_COUNT_USE.intValue())));
  }
}
