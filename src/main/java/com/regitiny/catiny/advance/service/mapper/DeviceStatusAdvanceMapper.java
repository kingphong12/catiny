package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.DeviceStatusModel;
import com.regitiny.catiny.domain.DeviceStatus;
import com.regitiny.catiny.service.dto.DeviceStatusDTO;
import com.regitiny.catiny.service.mapper.DeviceStatusMapper;
import com.regitiny.catiny.service.mapper.DeviceStatusMapperImpl;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface DeviceStatusAdvanceMapper extends EntityAdvanceMapper<DeviceStatusModel, DeviceStatusDTO, DeviceStatus>
{
  DeviceStatusMapper baseMapper = new DeviceStatusMapperImpl();

  DeviceStatusAdvanceMapper thisMapper = new DeviceStatusAdvanceMapperImpl();


  DeviceStatusDTO request2d(DeviceStatusModel.Request request);


  List<DeviceStatusDTO> request2d(List<DeviceStatusModel.Request> request);


  DeviceStatusModel.Response d2Response(DeviceStatusDTO dto);


  List<DeviceStatusModel.Response> d2Response(List<DeviceStatusDTO> dto);


  @Override
  default DeviceStatusModel e2m(DeviceStatus entity)
  {
    return thisMapper.d2m(baseMapper.toDto(entity));
  }


  @Override
  default List<DeviceStatusModel> e2m(List<DeviceStatus> entityList)
  {
    return thisMapper.d2m(baseMapper.toDto(entityList));
  }


  @Override
  default DeviceStatus d2e(DeviceStatusDTO dto)
  {
    return baseMapper.toEntity(dto);
  }


  @Override
  default List<DeviceStatus> d2e(List<DeviceStatusDTO> dtoList)
  {
    return baseMapper.toEntity(dtoList);
  }


  @Override
  default DeviceStatusDTO e2d(DeviceStatus entity)
  {
    return baseMapper.toDto(entity);
  }


  @Override
  default List<DeviceStatusDTO> e2d(List<DeviceStatus> entityList)
  {
    return baseMapper.toDto(entityList);
  }
}
