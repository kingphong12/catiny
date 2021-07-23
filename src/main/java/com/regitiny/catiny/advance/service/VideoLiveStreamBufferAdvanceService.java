package com.regitiny.catiny.advance.service;

import com.regitiny.catiny.advance.repository.VideoLiveStreamBufferAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.VideoLiveStreamBufferAdvanceSearch;
import com.regitiny.catiny.advance.service.mapper.VideoLiveStreamBufferAdvanceMapper;
import com.regitiny.catiny.domain.VideoLiveStreamBuffer;
import com.regitiny.catiny.service.VideoLiveStreamBufferQueryService;
import com.regitiny.catiny.service.VideoLiveStreamBufferService;

/**
 * AdvanceService layer for {@link VideoLiveStreamBuffer}.
 *
 * @see VideoLiveStreamBufferService is base service generate by jhipster
 */
public interface VideoLiveStreamBufferAdvanceService extends BaseSrvice<VideoLiveStreamBuffer, VideoLiveStreamBufferService, VideoLiveStreamBufferQueryService, VideoLiveStreamBufferAdvanceMapper, VideoLiveStreamBufferAdvanceRepository, VideoLiveStreamBufferAdvanceSearch>
{
}
