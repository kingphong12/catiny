package com.regitiny.catiny.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.IntegrationTest;
import com.regitiny.catiny.domain.BaseInfo;
import com.regitiny.catiny.domain.Video;
import com.regitiny.catiny.domain.VideoLiveStreamBuffer;
import com.regitiny.catiny.domain.VideoStream;
import com.regitiny.catiny.repository.VideoStreamRepository;
import com.regitiny.catiny.repository.search.VideoStreamSearchRepository;
import com.regitiny.catiny.service.criteria.VideoStreamCriteria;
import com.regitiny.catiny.service.dto.VideoStreamDTO;
import com.regitiny.catiny.service.mapper.VideoStreamMapper;
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

/**
 * Integration tests for the {@link VideoStreamResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
@GeneratedByJHipster
class VideoStreamResourceIT {

  private static final UUID DEFAULT_UUID = UUID.randomUUID();
  private static final UUID UPDATED_UUID = UUID.randomUUID();

  private static final Boolean DEFAULT_IS_LIVESTREAMING = false;
  private static final Boolean UPDATED_IS_LIVESTREAMING = true;

  private static final String ENTITY_API_URL = "/api/video-streams";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
  private static final String ENTITY_SEARCH_API_URL = "/api/_search/video-streams";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

  @Autowired
  private VideoStreamRepository videoStreamRepository;

  @Autowired
  private VideoStreamMapper videoStreamMapper;

  /**
   * This repository is mocked in the com.regitiny.catiny.repository.search test package.
   *
   * @see com.regitiny.catiny.repository.search.VideoStreamSearchRepositoryMockConfiguration
   */
  @Autowired
  private VideoStreamSearchRepository mockVideoStreamSearchRepository;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restVideoStreamMockMvc;

  private VideoStream videoStream;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static VideoStream createEntity(EntityManager em) {
    VideoStream videoStream = new VideoStream().uuid(DEFAULT_UUID).isLivestreaming(DEFAULT_IS_LIVESTREAMING);
    return videoStream;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static VideoStream createUpdatedEntity(EntityManager em) {
    VideoStream videoStream = new VideoStream().uuid(UPDATED_UUID).isLivestreaming(UPDATED_IS_LIVESTREAMING);
    return videoStream;
  }

  @BeforeEach
  public void initTest() {
    videoStream = createEntity(em);
  }

  @Test
  @Transactional
  void createVideoStream() throws Exception {
    int databaseSizeBeforeCreate = videoStreamRepository.findAll().size();
    // Create the VideoStream
    VideoStreamDTO videoStreamDTO = videoStreamMapper.toDto(videoStream);
    restVideoStreamMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(videoStreamDTO)))
      .andExpect(status().isCreated());

    // Validate the VideoStream in the database
    List<VideoStream> videoStreamList = videoStreamRepository.findAll();
    assertThat(videoStreamList).hasSize(databaseSizeBeforeCreate + 1);
    VideoStream testVideoStream = videoStreamList.get(videoStreamList.size() - 1);
    assertThat(testVideoStream.getUuid()).isEqualTo(DEFAULT_UUID);
    assertThat(testVideoStream.getIsLivestreaming()).isEqualTo(DEFAULT_IS_LIVESTREAMING);

    // Validate the VideoStream in Elasticsearch
    verify(mockVideoStreamSearchRepository, times(1)).save(testVideoStream);
  }

  @Test
  @Transactional
  void createVideoStreamWithExistingId() throws Exception {
    // Create the VideoStream with an existing ID
    videoStream.setId(1L);
    VideoStreamDTO videoStreamDTO = videoStreamMapper.toDto(videoStream);

    int databaseSizeBeforeCreate = videoStreamRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restVideoStreamMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(videoStreamDTO)))
      .andExpect(status().isBadRequest());

    // Validate the VideoStream in the database
    List<VideoStream> videoStreamList = videoStreamRepository.findAll();
    assertThat(videoStreamList).hasSize(databaseSizeBeforeCreate);

    // Validate the VideoStream in Elasticsearch
    verify(mockVideoStreamSearchRepository, times(0)).save(videoStream);
  }

  @Test
  @Transactional
  void checkUuidIsRequired() throws Exception {
    int databaseSizeBeforeTest = videoStreamRepository.findAll().size();
    // set the field null
    videoStream.setUuid(null);

    // Create the VideoStream, which fails.
    VideoStreamDTO videoStreamDTO = videoStreamMapper.toDto(videoStream);

    restVideoStreamMockMvc
      .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(videoStreamDTO)))
      .andExpect(status().isBadRequest());

    List<VideoStream> videoStreamList = videoStreamRepository.findAll();
    assertThat(videoStreamList).hasSize(databaseSizeBeforeTest);
  }

  @Test
  @Transactional
  void getAllVideoStreams() throws Exception {
    // Initialize the database
    videoStreamRepository.saveAndFlush(videoStream);

    // Get all the videoStreamList
    restVideoStreamMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(videoStream.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].isLivestreaming").value(hasItem(DEFAULT_IS_LIVESTREAMING.booleanValue())));
  }

  @Test
  @Transactional
  void getVideoStream() throws Exception {
    // Initialize the database
    videoStreamRepository.saveAndFlush(videoStream);

    // Get the videoStream
    restVideoStreamMockMvc
      .perform(get(ENTITY_API_URL_ID, videoStream.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(videoStream.getId().intValue()))
      .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()))
      .andExpect(jsonPath("$.isLivestreaming").value(DEFAULT_IS_LIVESTREAMING.booleanValue()));
  }

  @Test
  @Transactional
  void getVideoStreamsByIdFiltering() throws Exception {
    // Initialize the database
    videoStreamRepository.saveAndFlush(videoStream);

    Long id = videoStream.getId();

    defaultVideoStreamShouldBeFound("id.equals=" + id);
    defaultVideoStreamShouldNotBeFound("id.notEquals=" + id);

    defaultVideoStreamShouldBeFound("id.greaterThanOrEqual=" + id);
    defaultVideoStreamShouldNotBeFound("id.greaterThan=" + id);

    defaultVideoStreamShouldBeFound("id.lessThanOrEqual=" + id);
    defaultVideoStreamShouldNotBeFound("id.lessThan=" + id);
  }

  @Test
  @Transactional
  void getAllVideoStreamsByUuidIsEqualToSomething() throws Exception {
    // Initialize the database
    videoStreamRepository.saveAndFlush(videoStream);

    // Get all the videoStreamList where uuid equals to DEFAULT_UUID
    defaultVideoStreamShouldBeFound("uuid.equals=" + DEFAULT_UUID);

    // Get all the videoStreamList where uuid equals to UPDATED_UUID
    defaultVideoStreamShouldNotBeFound("uuid.equals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllVideoStreamsByUuidIsNotEqualToSomething() throws Exception {
    // Initialize the database
    videoStreamRepository.saveAndFlush(videoStream);

    // Get all the videoStreamList where uuid not equals to DEFAULT_UUID
    defaultVideoStreamShouldNotBeFound("uuid.notEquals=" + DEFAULT_UUID);

    // Get all the videoStreamList where uuid not equals to UPDATED_UUID
    defaultVideoStreamShouldBeFound("uuid.notEquals=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllVideoStreamsByUuidIsInShouldWork() throws Exception {
    // Initialize the database
    videoStreamRepository.saveAndFlush(videoStream);

    // Get all the videoStreamList where uuid in DEFAULT_UUID or UPDATED_UUID
    defaultVideoStreamShouldBeFound("uuid.in=" + DEFAULT_UUID + "," + UPDATED_UUID);

    // Get all the videoStreamList where uuid equals to UPDATED_UUID
    defaultVideoStreamShouldNotBeFound("uuid.in=" + UPDATED_UUID);
  }

  @Test
  @Transactional
  void getAllVideoStreamsByUuidIsNullOrNotNull() throws Exception {
    // Initialize the database
    videoStreamRepository.saveAndFlush(videoStream);

    // Get all the videoStreamList where uuid is not null
    defaultVideoStreamShouldBeFound("uuid.specified=true");

    // Get all the videoStreamList where uuid is null
    defaultVideoStreamShouldNotBeFound("uuid.specified=false");
  }

  @Test
  @Transactional
  void getAllVideoStreamsByIsLivestreamingIsEqualToSomething() throws Exception {
    // Initialize the database
    videoStreamRepository.saveAndFlush(videoStream);

    // Get all the videoStreamList where isLivestreaming equals to DEFAULT_IS_LIVESTREAMING
    defaultVideoStreamShouldBeFound("isLivestreaming.equals=" + DEFAULT_IS_LIVESTREAMING);

    // Get all the videoStreamList where isLivestreaming equals to UPDATED_IS_LIVESTREAMING
    defaultVideoStreamShouldNotBeFound("isLivestreaming.equals=" + UPDATED_IS_LIVESTREAMING);
  }

  @Test
  @Transactional
  void getAllVideoStreamsByIsLivestreamingIsNotEqualToSomething() throws Exception {
    // Initialize the database
    videoStreamRepository.saveAndFlush(videoStream);

    // Get all the videoStreamList where isLivestreaming not equals to DEFAULT_IS_LIVESTREAMING
    defaultVideoStreamShouldNotBeFound("isLivestreaming.notEquals=" + DEFAULT_IS_LIVESTREAMING);

    // Get all the videoStreamList where isLivestreaming not equals to UPDATED_IS_LIVESTREAMING
    defaultVideoStreamShouldBeFound("isLivestreaming.notEquals=" + UPDATED_IS_LIVESTREAMING);
  }

  @Test
  @Transactional
  void getAllVideoStreamsByIsLivestreamingIsInShouldWork() throws Exception {
    // Initialize the database
    videoStreamRepository.saveAndFlush(videoStream);

    // Get all the videoStreamList where isLivestreaming in DEFAULT_IS_LIVESTREAMING or UPDATED_IS_LIVESTREAMING
    defaultVideoStreamShouldBeFound("isLivestreaming.in=" + DEFAULT_IS_LIVESTREAMING + "," + UPDATED_IS_LIVESTREAMING);

    // Get all the videoStreamList where isLivestreaming equals to UPDATED_IS_LIVESTREAMING
    defaultVideoStreamShouldNotBeFound("isLivestreaming.in=" + UPDATED_IS_LIVESTREAMING);
  }

  @Test
  @Transactional
  void getAllVideoStreamsByIsLivestreamingIsNullOrNotNull() throws Exception {
    // Initialize the database
    videoStreamRepository.saveAndFlush(videoStream);

    // Get all the videoStreamList where isLivestreaming is not null
    defaultVideoStreamShouldBeFound("isLivestreaming.specified=true");

    // Get all the videoStreamList where isLivestreaming is null
    defaultVideoStreamShouldNotBeFound("isLivestreaming.specified=false");
  }

  @Test
  @Transactional
  void getAllVideoStreamsByVideoIsEqualToSomething() throws Exception {
    // Initialize the database
    videoStreamRepository.saveAndFlush(videoStream);
    Video video;
    if (TestUtil.findAll(em, Video.class).isEmpty()) {
      video = VideoResourceIT.createEntity(em);
      em.persist(video);
      em.flush();
    } else {
      video = TestUtil.findAll(em, Video.class).get(0);
    }
    em.persist(video);
    em.flush();
    videoStream.setVideo(video);
    videoStreamRepository.saveAndFlush(videoStream);
    Long videoId = video.getId();

    // Get all the videoStreamList where video equals to videoId
    defaultVideoStreamShouldBeFound("videoId.equals=" + videoId);

    // Get all the videoStreamList where video equals to (videoId + 1)
    defaultVideoStreamShouldNotBeFound("videoId.equals=" + (videoId + 1));
  }

  @Test
  @Transactional
  void getAllVideoStreamsByInfoIsEqualToSomething() throws Exception {
    // Initialize the database
    videoStreamRepository.saveAndFlush(videoStream);
    BaseInfo info;
    if (TestUtil.findAll(em, BaseInfo.class).isEmpty()) {
      info = BaseInfoResourceIT.createEntity(em);
      em.persist(info);
      em.flush();
    } else {
      info = TestUtil.findAll(em, BaseInfo.class).get(0);
    }
    em.persist(info);
    em.flush();
    videoStream.setInfo(info);
    videoStreamRepository.saveAndFlush(videoStream);
    Long infoId = info.getId();

    // Get all the videoStreamList where info equals to infoId
    defaultVideoStreamShouldBeFound("infoId.equals=" + infoId);

    // Get all the videoStreamList where info equals to (infoId + 1)
    defaultVideoStreamShouldNotBeFound("infoId.equals=" + (infoId + 1));
  }

  @Test
  @Transactional
  void getAllVideoStreamsByVideoLiveStreamBufferIsEqualToSomething() throws Exception {
    // Initialize the database
    videoStreamRepository.saveAndFlush(videoStream);
    VideoLiveStreamBuffer videoLiveStreamBuffer;
    if (TestUtil.findAll(em, VideoLiveStreamBuffer.class).isEmpty()) {
      videoLiveStreamBuffer = VideoLiveStreamBufferResourceIT.createEntity(em);
      em.persist(videoLiveStreamBuffer);
      em.flush();
    } else {
      videoLiveStreamBuffer = TestUtil.findAll(em, VideoLiveStreamBuffer.class).get(0);
    }
    em.persist(videoLiveStreamBuffer);
    em.flush();
    videoStream.addVideoLiveStreamBuffer(videoLiveStreamBuffer);
    videoStreamRepository.saveAndFlush(videoStream);
    Long videoLiveStreamBufferId = videoLiveStreamBuffer.getId();

    // Get all the videoStreamList where videoLiveStreamBuffer equals to videoLiveStreamBufferId
    defaultVideoStreamShouldBeFound("videoLiveStreamBufferId.equals=" + videoLiveStreamBufferId);

    // Get all the videoStreamList where videoLiveStreamBuffer equals to (videoLiveStreamBufferId + 1)
    defaultVideoStreamShouldNotBeFound("videoLiveStreamBufferId.equals=" + (videoLiveStreamBufferId + 1));
  }

  /**
   * Executes the search, and checks that the default entity is returned.
   */
  private void defaultVideoStreamShouldBeFound(String filter) throws Exception {
    restVideoStreamMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(videoStream.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].isLivestreaming").value(hasItem(DEFAULT_IS_LIVESTREAMING.booleanValue())));

    // Check, that the count call also returns 1
    restVideoStreamMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("1"));
  }

  /**
   * Executes the search, and checks that the default entity is not returned.
   */
  private void defaultVideoStreamShouldNotBeFound(String filter) throws Exception {
    restVideoStreamMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$").isArray())
      .andExpect(jsonPath("$").isEmpty());

    // Check, that the count call also returns 0
    restVideoStreamMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("0"));
  }

  @Test
  @Transactional
  void getNonExistingVideoStream() throws Exception {
    // Get the videoStream
    restVideoStreamMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewVideoStream() throws Exception {
    // Initialize the database
    videoStreamRepository.saveAndFlush(videoStream);

    int databaseSizeBeforeUpdate = videoStreamRepository.findAll().size();

    // Update the videoStream
    VideoStream updatedVideoStream = videoStreamRepository.findById(videoStream.getId()).get();
    // Disconnect from session so that the updates on updatedVideoStream are not directly saved in db
    em.detach(updatedVideoStream);
    updatedVideoStream.uuid(UPDATED_UUID).isLivestreaming(UPDATED_IS_LIVESTREAMING);
    VideoStreamDTO videoStreamDTO = videoStreamMapper.toDto(updatedVideoStream);

    restVideoStreamMockMvc
      .perform(
        put(ENTITY_API_URL_ID, videoStreamDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(videoStreamDTO))
      )
      .andExpect(status().isOk());

    // Validate the VideoStream in the database
    List<VideoStream> videoStreamList = videoStreamRepository.findAll();
    assertThat(videoStreamList).hasSize(databaseSizeBeforeUpdate);
    VideoStream testVideoStream = videoStreamList.get(videoStreamList.size() - 1);
    assertThat(testVideoStream.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testVideoStream.getIsLivestreaming()).isEqualTo(UPDATED_IS_LIVESTREAMING);

    // Validate the VideoStream in Elasticsearch
    verify(mockVideoStreamSearchRepository).save(testVideoStream);
  }

  @Test
  @Transactional
  void putNonExistingVideoStream() throws Exception {
    int databaseSizeBeforeUpdate = videoStreamRepository.findAll().size();
    videoStream.setId(count.incrementAndGet());

    // Create the VideoStream
    VideoStreamDTO videoStreamDTO = videoStreamMapper.toDto(videoStream);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restVideoStreamMockMvc
      .perform(
        put(ENTITY_API_URL_ID, videoStreamDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(videoStreamDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the VideoStream in the database
    List<VideoStream> videoStreamList = videoStreamRepository.findAll();
    assertThat(videoStreamList).hasSize(databaseSizeBeforeUpdate);

    // Validate the VideoStream in Elasticsearch
    verify(mockVideoStreamSearchRepository, times(0)).save(videoStream);
  }

  @Test
  @Transactional
  void putWithIdMismatchVideoStream() throws Exception {
    int databaseSizeBeforeUpdate = videoStreamRepository.findAll().size();
    videoStream.setId(count.incrementAndGet());

    // Create the VideoStream
    VideoStreamDTO videoStreamDTO = videoStreamMapper.toDto(videoStream);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restVideoStreamMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(videoStreamDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the VideoStream in the database
    List<VideoStream> videoStreamList = videoStreamRepository.findAll();
    assertThat(videoStreamList).hasSize(databaseSizeBeforeUpdate);

    // Validate the VideoStream in Elasticsearch
    verify(mockVideoStreamSearchRepository, times(0)).save(videoStream);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamVideoStream() throws Exception {
    int databaseSizeBeforeUpdate = videoStreamRepository.findAll().size();
    videoStream.setId(count.incrementAndGet());

    // Create the VideoStream
    VideoStreamDTO videoStreamDTO = videoStreamMapper.toDto(videoStream);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restVideoStreamMockMvc
      .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(videoStreamDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the VideoStream in the database
    List<VideoStream> videoStreamList = videoStreamRepository.findAll();
    assertThat(videoStreamList).hasSize(databaseSizeBeforeUpdate);

    // Validate the VideoStream in Elasticsearch
    verify(mockVideoStreamSearchRepository, times(0)).save(videoStream);
  }

  @Test
  @Transactional
  void partialUpdateVideoStreamWithPatch() throws Exception {
    // Initialize the database
    videoStreamRepository.saveAndFlush(videoStream);

    int databaseSizeBeforeUpdate = videoStreamRepository.findAll().size();

    // Update the videoStream using partial update
    VideoStream partialUpdatedVideoStream = new VideoStream();
    partialUpdatedVideoStream.setId(videoStream.getId());

    partialUpdatedVideoStream.uuid(UPDATED_UUID);

    restVideoStreamMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedVideoStream.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedVideoStream))
      )
      .andExpect(status().isOk());

    // Validate the VideoStream in the database
    List<VideoStream> videoStreamList = videoStreamRepository.findAll();
    assertThat(videoStreamList).hasSize(databaseSizeBeforeUpdate);
    VideoStream testVideoStream = videoStreamList.get(videoStreamList.size() - 1);
    assertThat(testVideoStream.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testVideoStream.getIsLivestreaming()).isEqualTo(DEFAULT_IS_LIVESTREAMING);
  }

  @Test
  @Transactional
  void fullUpdateVideoStreamWithPatch() throws Exception {
    // Initialize the database
    videoStreamRepository.saveAndFlush(videoStream);

    int databaseSizeBeforeUpdate = videoStreamRepository.findAll().size();

    // Update the videoStream using partial update
    VideoStream partialUpdatedVideoStream = new VideoStream();
    partialUpdatedVideoStream.setId(videoStream.getId());

    partialUpdatedVideoStream.uuid(UPDATED_UUID).isLivestreaming(UPDATED_IS_LIVESTREAMING);

    restVideoStreamMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedVideoStream.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedVideoStream))
      )
      .andExpect(status().isOk());

    // Validate the VideoStream in the database
    List<VideoStream> videoStreamList = videoStreamRepository.findAll();
    assertThat(videoStreamList).hasSize(databaseSizeBeforeUpdate);
    VideoStream testVideoStream = videoStreamList.get(videoStreamList.size() - 1);
    assertThat(testVideoStream.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testVideoStream.getIsLivestreaming()).isEqualTo(UPDATED_IS_LIVESTREAMING);
  }

  @Test
  @Transactional
  void patchNonExistingVideoStream() throws Exception {
    int databaseSizeBeforeUpdate = videoStreamRepository.findAll().size();
    videoStream.setId(count.incrementAndGet());

    // Create the VideoStream
    VideoStreamDTO videoStreamDTO = videoStreamMapper.toDto(videoStream);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restVideoStreamMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, videoStreamDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(videoStreamDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the VideoStream in the database
    List<VideoStream> videoStreamList = videoStreamRepository.findAll();
    assertThat(videoStreamList).hasSize(databaseSizeBeforeUpdate);

    // Validate the VideoStream in Elasticsearch
    verify(mockVideoStreamSearchRepository, times(0)).save(videoStream);
  }

  @Test
  @Transactional
  void patchWithIdMismatchVideoStream() throws Exception {
    int databaseSizeBeforeUpdate = videoStreamRepository.findAll().size();
    videoStream.setId(count.incrementAndGet());

    // Create the VideoStream
    VideoStreamDTO videoStreamDTO = videoStreamMapper.toDto(videoStream);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restVideoStreamMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(videoStreamDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the VideoStream in the database
    List<VideoStream> videoStreamList = videoStreamRepository.findAll();
    assertThat(videoStreamList).hasSize(databaseSizeBeforeUpdate);

    // Validate the VideoStream in Elasticsearch
    verify(mockVideoStreamSearchRepository, times(0)).save(videoStream);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamVideoStream() throws Exception {
    int databaseSizeBeforeUpdate = videoStreamRepository.findAll().size();
    videoStream.setId(count.incrementAndGet());

    // Create the VideoStream
    VideoStreamDTO videoStreamDTO = videoStreamMapper.toDto(videoStream);

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restVideoStreamMockMvc
      .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(videoStreamDTO)))
      .andExpect(status().isMethodNotAllowed());

    // Validate the VideoStream in the database
    List<VideoStream> videoStreamList = videoStreamRepository.findAll();
    assertThat(videoStreamList).hasSize(databaseSizeBeforeUpdate);

    // Validate the VideoStream in Elasticsearch
    verify(mockVideoStreamSearchRepository, times(0)).save(videoStream);
  }

  @Test
  @Transactional
  void deleteVideoStream() throws Exception {
    // Initialize the database
    videoStreamRepository.saveAndFlush(videoStream);

    int databaseSizeBeforeDelete = videoStreamRepository.findAll().size();

    // Delete the videoStream
    restVideoStreamMockMvc
      .perform(delete(ENTITY_API_URL_ID, videoStream.getId()).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<VideoStream> videoStreamList = videoStreamRepository.findAll();
    assertThat(videoStreamList).hasSize(databaseSizeBeforeDelete - 1);

    // Validate the VideoStream in Elasticsearch
    verify(mockVideoStreamSearchRepository, times(1)).deleteById(videoStream.getId());
  }

  @Test
  @Transactional
  void searchVideoStream() throws Exception {
    // Configure the mock search repository
    // Initialize the database
    videoStreamRepository.saveAndFlush(videoStream);
    when(mockVideoStreamSearchRepository.search("id:" + videoStream.getId(), PageRequest.of(0, 20)))
      .thenReturn(new PageImpl<>(Collections.singletonList(videoStream), PageRequest.of(0, 1), 1));

    // Search the videoStream
    restVideoStreamMockMvc
      .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + videoStream.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(videoStream.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].isLivestreaming").value(hasItem(DEFAULT_IS_LIVESTREAMING.booleanValue())));
  }
}
