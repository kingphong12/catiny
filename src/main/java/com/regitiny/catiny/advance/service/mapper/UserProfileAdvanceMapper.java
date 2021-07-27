package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.UserProfileModel;
import com.regitiny.catiny.domain.UserProfile;
import com.regitiny.catiny.service.dto.UserProfileDTO;
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
public interface UserProfileAdvanceMapper extends EntityAdvanceMapper<UserProfileModel, UserProfileDTO, UserProfile>
{
  UserProfileDTO request2d(UserProfileModel.Request request);


  List<UserProfileDTO> request2d(List<UserProfileModel.Request> request);


  UserProfileModel.Response d2Response(UserProfileDTO dto);


  List<UserProfileModel.Response> d2Response(List<UserProfileDTO> dto);
}
