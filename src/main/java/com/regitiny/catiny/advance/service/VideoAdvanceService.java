package com.regitiny.catiny.advance.service;

import com.regitiny.catiny.advance.repository.VideoAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.VideoAdvanceSearch;
import com.regitiny.catiny.advance.service.mapper.VideoAdvanceMapper;
import com.regitiny.catiny.domain.Video;
import com.regitiny.catiny.service.VideoQueryService;
import com.regitiny.catiny.service.VideoService;

/**
 * AdvanceService layer for {@link Video}.
 *
 * @see VideoService is base service generate by jhipster
 */
public interface VideoAdvanceService extends BaseSrvice<Video, VideoService, VideoQueryService, VideoAdvanceMapper, VideoAdvanceRepository, VideoAdvanceSearch>
{
}
