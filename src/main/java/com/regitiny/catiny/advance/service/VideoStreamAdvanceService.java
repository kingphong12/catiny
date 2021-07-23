package com.regitiny.catiny.advance.service;

import com.regitiny.catiny.advance.repository.VideoStreamAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.VideoStreamAdvanceSearch;
import com.regitiny.catiny.advance.service.mapper.VideoStreamAdvanceMapper;
import com.regitiny.catiny.domain.VideoStream;
import com.regitiny.catiny.service.VideoStreamQueryService;
import com.regitiny.catiny.service.VideoStreamService;

/**
 * AdvanceService layer for {@link VideoStream}.
 *
 * @see VideoStreamService is base service generate by jhipster
 */
public interface VideoStreamAdvanceService extends BaseSrvice<VideoStream, VideoStreamService, VideoStreamQueryService, VideoStreamAdvanceMapper, VideoStreamAdvanceRepository, VideoStreamAdvanceSearch>
{
}
