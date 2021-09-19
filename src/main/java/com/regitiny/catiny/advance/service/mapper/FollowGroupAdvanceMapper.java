package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.FollowGroupModel;
import com.regitiny.catiny.domain.FollowGroup;
import com.regitiny.catiny.service.dto.FollowGroupDTO;
import org.mapstruct.Mapper;

/**
 * this is a custom mapper for each entity .
 * this mapper extend from Jhipster mapper
 * ( d=dto, e=entity , m=model and To=2 ) --> ( dtoToEntity = d2e , modelToDto = m2d ).
 */
@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface FollowGroupAdvanceMapper extends EntityAdvanceMapper<FollowGroupModel, FollowGroupDTO, FollowGroup>
{
}
