package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.VideoStreamAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.VideoStreamAdvanceSearch;
import com.regitiny.catiny.advance.service.VideoStreamAdvanceService;
import com.regitiny.catiny.advance.service.mapper.VideoStreamAdvanceMapper;
import com.regitiny.catiny.domain.VideoStream;
import com.regitiny.catiny.service.VideoStreamQueryService;
import com.regitiny.catiny.service.VideoStreamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class VideoStreamAdvanceServiceImpl extends AdvanceService<VideoStream, VideoStreamService, VideoStreamQueryService, VideoStreamAdvanceMapper, VideoStreamAdvanceRepository, VideoStreamAdvanceSearch> implements VideoStreamAdvanceService
{
  private final VideoStreamAdvanceRepository videoStreamAdvanceRepository;

  private final VideoStreamAdvanceSearch videoStreamAdvanceSearch;

  private final VideoStreamAdvanceMapper videoStreamAdvanceMapper;

  protected VideoStream createLivestream()
  {
    return videoStreamAdvanceRepository.save(new VideoStream());
  }
}
