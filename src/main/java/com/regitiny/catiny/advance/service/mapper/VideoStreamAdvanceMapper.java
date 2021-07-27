package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.VideoStreamModel;
import com.regitiny.catiny.domain.VideoStream;
import com.regitiny.catiny.service.dto.VideoStreamDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * this is a custom mapper for each entity .
 * this mapper extend from Jhipster mapper
 * ( d=dto, e=entity , m=model and To=2 ) --> ( dtoToEntity = d2e , modelToDto = m2d ).
 */
@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface VideoStreamAdvanceMapper extends EntityAdvanceMapper<VideoStreamModel, VideoStreamDTO, VideoStream>
{
  VideoStreamDTO request2d(VideoStreamModel.Request request);


  List<VideoStreamDTO> request2d(List<VideoStreamModel.Request> request);


  VideoStreamModel.Response d2Response(VideoStreamDTO dto);


  List<VideoStreamModel.Response> d2Response(List<VideoStreamDTO> dto);
}
