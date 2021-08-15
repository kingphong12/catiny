package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.PermissionModel;
import com.regitiny.catiny.domain.Permission;
import com.regitiny.catiny.service.dto.PermissionDTO;
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
public interface PermissionAdvanceMapper extends EntityAdvanceMapper<PermissionModel, PermissionDTO, Permission>
{
}
