package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.VideoLiveStreamBufferModel;
import com.regitiny.catiny.domain.VideoLiveStreamBuffer;
import com.regitiny.catiny.service.dto.VideoLiveStreamBufferDTO;
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
public interface VideoLiveStreamBufferAdvanceMapper extends EntityAdvanceMapper<VideoLiveStreamBufferModel, VideoLiveStreamBufferDTO, VideoLiveStreamBuffer>
{
  VideoLiveStreamBufferDTO request2d(VideoLiveStreamBufferModel.Request request);


  List<VideoLiveStreamBufferDTO> request2d(List<VideoLiveStreamBufferModel.Request> request);


  VideoLiveStreamBufferModel.Response d2Response(VideoLiveStreamBufferDTO dto);


  List<VideoLiveStreamBufferModel.Response> d2Response(List<VideoLiveStreamBufferDTO> dto);
}
