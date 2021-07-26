package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.VideoLiveStreamBufferModel;
import com.regitiny.catiny.domain.VideoLiveStreamBuffer;
import com.regitiny.catiny.service.dto.VideoLiveStreamBufferDTO;
import com.regitiny.catiny.service.mapper.VideoLiveStreamBufferMapper;
import com.regitiny.catiny.service.mapper.VideoLiveStreamBufferMapperImpl;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface VideoLiveStreamBufferAdvanceMapper extends EntityAdvanceMapper<VideoLiveStreamBufferModel, VideoLiveStreamBufferDTO, VideoLiveStreamBuffer>
{
  VideoLiveStreamBufferMapper baseMapper = new VideoLiveStreamBufferMapperImpl();

  VideoLiveStreamBufferAdvanceMapper thisMapper = new VideoLiveStreamBufferAdvanceMapperImpl();


  VideoLiveStreamBufferDTO request2d(VideoLiveStreamBufferModel.Request request);


  List<VideoLiveStreamBufferDTO> request2d(List<VideoLiveStreamBufferModel.Request> request);


  VideoLiveStreamBufferModel.Response d2Response(VideoLiveStreamBufferDTO dto);


  List<VideoLiveStreamBufferModel.Response> d2Response(List<VideoLiveStreamBufferDTO> dto);


  @Override
  default VideoLiveStreamBufferModel e2m(VideoLiveStreamBuffer entity)
  {
    return thisMapper.d2m(baseMapper.toDto(entity));
  }


  @Override
  default List<VideoLiveStreamBufferModel> e2m(List<VideoLiveStreamBuffer> entityList)
  {
    return thisMapper.d2m(baseMapper.toDto(entityList));
  }


  @Override
  default VideoLiveStreamBuffer d2e(VideoLiveStreamBufferDTO dto)
  {
    return baseMapper.toEntity(dto);
  }


  @Override
  default List<VideoLiveStreamBuffer> d2e(List<VideoLiveStreamBufferDTO> dtoList)
  {
    return baseMapper.toEntity(dtoList);
  }


  @Override
  default VideoLiveStreamBufferDTO e2d(VideoLiveStreamBuffer entity)
  {
    return baseMapper.toDto(entity);
  }


  @Override
  default List<VideoLiveStreamBufferDTO> e2d(List<VideoLiveStreamBuffer> entityList)
  {
    return baseMapper.toDto(entityList);
  }
}
