package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.FollowUserModel;
import com.regitiny.catiny.domain.FollowUser;
import com.regitiny.catiny.service.dto.FollowUserDTO;
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
public interface FollowUserAdvanceMapper extends EntityAdvanceMapper<FollowUserModel, FollowUserDTO, FollowUser>
{
  FollowUserDTO request2d(FollowUserModel.Request request);


  List<FollowUserDTO> request2d(List<FollowUserModel.Request> request);


  FollowUserModel.Response d2Response(FollowUserDTO dto);


  List<FollowUserModel.Response> d2Response(List<FollowUserDTO> dto);
}
