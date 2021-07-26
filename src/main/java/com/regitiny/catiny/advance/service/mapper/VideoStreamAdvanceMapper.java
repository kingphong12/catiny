package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.VideoStreamModel;
import com.regitiny.catiny.domain.VideoStream;
import com.regitiny.catiny.service.dto.VideoStreamDTO;
import com.regitiny.catiny.service.mapper.VideoStreamMapper;
import com.regitiny.catiny.service.mapper.VideoStreamMapperImpl;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface VideoStreamAdvanceMapper extends EntityAdvanceMapper<VideoStreamModel, VideoStreamDTO, VideoStream>
{
  VideoStreamMapper baseMapper = new VideoStreamMapperImpl();

  VideoStreamAdvanceMapper thisMapper = new VideoStreamAdvanceMapperImpl();


  VideoStreamDTO request2d(VideoStreamModel.Request request);


  List<VideoStreamDTO> request2d(List<VideoStreamModel.Request> request);


  VideoStreamModel.Response d2Response(VideoStreamDTO dto);


  List<VideoStreamModel.Response> d2Response(List<VideoStreamDTO> dto);


  @Override
  default VideoStreamModel e2m(VideoStream entity)
  {
    return thisMapper.d2m(baseMapper.toDto(entity));
  }


  @Override
  default List<VideoStreamModel> e2m(List<VideoStream> entityList)
  {
    return thisMapper.d2m(baseMapper.toDto(entityList));
  }


  @Override
  default VideoStream d2e(VideoStreamDTO dto)
  {
    return baseMapper.toEntity(dto);
  }


  @Override
  default List<VideoStream> d2e(List<VideoStreamDTO> dtoList)
  {
    return baseMapper.toEntity(dtoList);
  }


  @Override
  default VideoStreamDTO e2d(VideoStream entity)
  {
    return baseMapper.toDto(entity);
  }


  @Override
  default List<VideoStreamDTO> e2d(List<VideoStream> entityList)
  {
    return baseMapper.toDto(entityList);
  }
}
