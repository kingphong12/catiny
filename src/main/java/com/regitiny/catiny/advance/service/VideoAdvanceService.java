package com.regitiny.catiny.advance.service;

import com.regitiny.catiny.advance.repository.VideoAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.VideoAdvanceSearch;
import com.regitiny.catiny.advance.service.mapper.VideoAdvanceMapper;
import com.regitiny.catiny.domain.Video;
import com.regitiny.catiny.service.VideoQueryService;
import com.regitiny.catiny.service.VideoService;
import com.regitiny.catiny.service.dto.VideoDTO;
import io.vavr.Tuple2;
import io.vavr.control.Option;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.web.multipart.MultipartFile;

/**
 * AdvanceService layer for {@link Video}.
 *
 * @see VideoService is base service generate by jhipster
 */
public interface VideoAdvanceService extends BaseSrvice<Video, VideoService, VideoQueryService, VideoAdvanceMapper, VideoAdvanceRepository, VideoAdvanceSearch>
{
  /**
   * upload 1 video , lưu dữ liệu vào ổ đĩa
   *
   * @param videoData   dữ liệu của video
   * @param desiredName tên muốn đặt
   * @return VideoDTO
   */
  Option<VideoDTO> upload(MultipartFile videoData, String desiredName);


  /**
   * đọc một phần video
   *
   * @param uuidOrName
   * @param range
   * @return
   */
  Tuple2<ResourceRegion, String> getResourceRegion(String uuidOrName, String range);


  Option<VideoDTO> createVideoLivestream();
}
