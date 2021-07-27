package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.FollowPageModel;
import com.regitiny.catiny.domain.FollowPage;
import com.regitiny.catiny.service.dto.FollowPageDTO;
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
public interface FollowPageAdvanceMapper extends EntityAdvanceMapper<FollowPageModel, FollowPageDTO, FollowPage>
{
  FollowPageDTO request2d(FollowPageModel.Request request);


  List<FollowPageDTO> request2d(List<FollowPageModel.Request> request);


  FollowPageModel.Response d2Response(FollowPageDTO dto);


  List<FollowPageModel.Response> d2Response(List<FollowPageDTO> dto);
}
