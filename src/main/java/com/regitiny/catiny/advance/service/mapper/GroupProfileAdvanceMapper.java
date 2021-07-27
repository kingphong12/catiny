package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.GroupProfileModel;
import com.regitiny.catiny.domain.GroupProfile;
import com.regitiny.catiny.service.dto.GroupProfileDTO;
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
public interface GroupProfileAdvanceMapper extends EntityAdvanceMapper<GroupProfileModel, GroupProfileDTO, GroupProfile>
{
  GroupProfileDTO request2d(GroupProfileModel.Request request);


  List<GroupProfileDTO> request2d(List<GroupProfileModel.Request> request);


  GroupProfileModel.Response d2Response(GroupProfileDTO dto);


  List<GroupProfileModel.Response> d2Response(List<GroupProfileDTO> dto);
}
