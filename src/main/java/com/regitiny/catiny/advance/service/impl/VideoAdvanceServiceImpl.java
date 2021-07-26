package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.VideoAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.VideoAdvanceSearch;
import com.regitiny.catiny.advance.service.VideoAdvanceService;
import com.regitiny.catiny.advance.service.mapper.VideoAdvanceMapper;
import com.regitiny.catiny.domain.Video;
import com.regitiny.catiny.service.VideoQueryService;
import com.regitiny.catiny.service.VideoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class VideoAdvanceServiceImpl extends AdvanceService<Video, VideoService, VideoQueryService, VideoAdvanceMapper, VideoAdvanceRepository, VideoAdvanceSearch> implements VideoAdvanceService
{
  private final VideoAdvanceRepository videoAdvanceRepository;

  private final VideoAdvanceSearch videoAdvanceSearch;

  private final VideoAdvanceMapper videoAdvanceMapper;
}
