package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.VideoModel;
import com.regitiny.catiny.domain.Video;
import com.regitiny.catiny.service.dto.VideoDTO;
import com.regitiny.catiny.service.mapper.VideoMapper;
import com.regitiny.catiny.service.mapper.VideoMapperImpl;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface VideoAdvanceMapper extends EntityAdvanceMapper<VideoModel, VideoDTO, Video>
{
  VideoMapper baseMapper = new VideoMapperImpl();

  VideoAdvanceMapper thisMapper = new VideoAdvanceMapperImpl();


  VideoDTO request2d(VideoModel.Request request);


  List<VideoDTO> request2d(List<VideoModel.Request> request);


  VideoModel.Response d2Response(VideoDTO dto);


  List<VideoModel.Response> d2Response(List<VideoDTO> dto);


  @Override
  default VideoModel e2m(Video entity)
  {
    return thisMapper.d2m(baseMapper.toDto(entity));
  }


  @Override
  default List<VideoModel> e2m(List<Video> entityList)
  {
    return thisMapper.d2m(baseMapper.toDto(entityList));
  }


  @Override
  default Video d2e(VideoDTO dto)
  {
    return baseMapper.toEntity(dto);
  }


  @Override
  default List<Video> d2e(List<VideoDTO> dtoList)
  {
    return baseMapper.toEntity(dtoList);
  }


  @Override
  default VideoDTO e2d(Video entity)
  {
    return baseMapper.toDto(entity);
  }


  @Override
  default List<VideoDTO> e2d(List<Video> entityList)
  {
    return baseMapper.toDto(entityList);
  }
}
