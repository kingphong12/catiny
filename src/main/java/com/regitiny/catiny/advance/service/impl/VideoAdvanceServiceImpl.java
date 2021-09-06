package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.VideoAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.VideoAdvanceSearch;
import com.regitiny.catiny.advance.service.VideoAdvanceService;
import com.regitiny.catiny.advance.service.mapper.VideoAdvanceMapper;
import com.regitiny.catiny.domain.Video;
import com.regitiny.catiny.domain.VideoStream;
import com.regitiny.catiny.service.VideoQueryService;
import com.regitiny.catiny.service.VideoService;
import com.regitiny.catiny.service.dto.VideoDTO;
import com.regitiny.catiny.web.rest.errors.NhechException;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.UUID;

import static java.lang.Math.min;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class VideoAdvanceServiceImpl extends AdvanceService<Video, VideoService, VideoQueryService, VideoAdvanceMapper, VideoAdvanceRepository, VideoAdvanceSearch> implements VideoAdvanceService
{
  private static final Integer RANGE_DEFAULT = 5 * 1024 * 1024;
  private final VideoAdvanceRepository videoAdvanceRepository;
  private final VideoAdvanceSearch videoAdvanceSearch;
  private final VideoAdvanceMapper videoAdvanceMapper;
  @Autowired
  private VideoStreamAdvanceServiceImpl videoStreamAdvanceService;
  @Autowired
  private VideoLiveStreamBufferAdvanceServiceImpl videoLiveStreamBufferAdvanceService;

  @Autowired
  private FileInfoAdvanceServiceImpl fileInfoAdvanceService;

  @Value("${catiny.server.ffmpeg.data.video.folder-original}")
  private String folderVideoOriginal;

  @Override
  public Option<VideoDTO> upload(MultipartFile videoData, String desiredName)
  {
    if (!Objects.requireNonNull(videoData.getContentType()).toLowerCase().contains("video/"))
    {
      log.warn("this isn't video. -> videoData.contenType = {}", videoData.getContentType());
      return Option.none();
    }
    var fileInfo = fileInfoAdvanceService.createAndSaveToDisk(desiredName, videoData, folderVideoOriginal);
    if (Objects.isNull(fileInfo))
      return Option.none();
    var imageSaved = videoAdvanceRepository.save(new Video().uuid(fileInfo.getUuid())
      .fileInfo(fileInfo)
      .dataSize(videoData.getSize())
      .quality(1F)
      .name(fileInfo.getNameFile()));
    return videoAdvanceMapper.e2o_d(imageSaved);
  }

  public Tuple2<ResourceRegion, String> getResourceRegion(String uuidOrName, String range)
  {
    var videoInfo = Try.of(() -> UUID.fromString(uuidOrName)).map(videoAdvanceRepository::findOneByUuid).getOrElse(videoAdvanceRepository.findOneByNameAndOriginalIsNull(uuidOrName));

    return videoInfo
      .map(video ->
      {
        String filePath = video.getFileInfo().getPath();
//        if (Boolean.TRUE.equals(file.getProcessed()) && Objects.nonNull(file.getPathFileProcessed()))
//          filePath = file.getPathFileProcessed();
//        else if (Objects.nonNull(file.getPathFileOriginal()))
//          filePath = file.getPathFileOriginal();
//        if (filePath == null)
//          throw new NhechException("Nhếch bảo chả biết cất cái video này ở đâu");
        Resource resource = new FileSystemResource(filePath);
        ResourceRegion resourceRegion;
        long contentLength = Try.of(resource::contentLength)
          .onFailure(throwable ->
          {
            log.debug("error video.contentLength or video no data", throwable);
            throw new NhechException("nhếch tìm đến nhà này chẳng thấy ai cả");
          })
          .getOrElse(0L);
        if (Objects.isNull(range))
          return Tuple.of(new ResourceRegion(resource, 0, contentLength), video.getFileInfo().getTypeFile());
        long fromRange = 0;
        long toRange = 0;
        if (StringUtils.isNotBlank(range))
        {
          String[] ranges = range
            .substring("bytes=".length())
            .split("-");
          fromRange = Integer.parseInt(ranges[0]);
          if (ranges.length > 1)
            toRange = Integer.parseInt(ranges[1]);
          else
            toRange = (int) (contentLength - 1);
        }
        if (fromRange > 0)
        {
          long rangeLength = min(RANGE_DEFAULT, toRange - fromRange + 1);
          resourceRegion = new ResourceRegion(resource, fromRange, rangeLength);
        }
        else
        {
          long rangeLength = min(RANGE_DEFAULT, contentLength - 1);
          resourceRegion = new ResourceRegion(resource, 0, rangeLength);
        }

        return Tuple.of(resourceRegion, video.getFileInfo().getTypeFile());
      }).getOrElse(Tuple.of(null, null));
  }

  @Override
  public Option<VideoDTO> createVideoLivestream()
  {
    var video = videoAdvanceRepository.save(new Video());
    videoStreamAdvanceService.local().advanceRepository.save(new VideoStream()
      .isLivestreaming(false).video(video));
    return Option.of(video).map(videoAdvanceMapper::e2d);
  }
}
