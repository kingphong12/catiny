package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.DeviceStatusModel;
import com.regitiny.catiny.domain.DeviceStatus;
import com.regitiny.catiny.service.dto.DeviceStatusDTO;
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
public interface DeviceStatusAdvanceMapper extends EntityAdvanceMapper<DeviceStatusModel, DeviceStatusDTO, DeviceStatus>
{
}
