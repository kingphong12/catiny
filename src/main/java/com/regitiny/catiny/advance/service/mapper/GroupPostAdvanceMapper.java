package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.GroupPostModel;
import com.regitiny.catiny.domain.GroupPost;
import com.regitiny.catiny.service.dto.GroupPostDTO;
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
public interface GroupPostAdvanceMapper extends EntityAdvanceMapper<GroupPostModel, GroupPostDTO, GroupPost>
{
  GroupPostDTO request2d(GroupPostModel.Request request);


  List<GroupPostDTO> request2d(List<GroupPostModel.Request> request);


  GroupPostModel.Response d2Response(GroupPostDTO dto);


  List<GroupPostModel.Response> d2Response(List<GroupPostDTO> dto);
}
