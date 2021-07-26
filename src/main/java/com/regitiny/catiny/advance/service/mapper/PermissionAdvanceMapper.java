package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.PermissionModel;
import com.regitiny.catiny.domain.Permission;
import com.regitiny.catiny.service.dto.PermissionDTO;
import com.regitiny.catiny.service.mapper.PermissionMapper;
import com.regitiny.catiny.service.mapper.PermissionMapperImpl;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface PermissionAdvanceMapper extends EntityAdvanceMapper<PermissionModel, PermissionDTO, Permission>
{
  PermissionMapper baseMapper = new PermissionMapperImpl();

  PermissionAdvanceMapper thisMapper = new PermissionAdvanceMapperImpl();


  PermissionDTO request2d(PermissionModel.Request request);


  List<PermissionDTO> request2d(List<PermissionModel.Request> request);


  PermissionModel.Response d2Response(PermissionDTO dto);


  List<PermissionModel.Response> d2Response(List<PermissionDTO> dto);


  @Override
  default PermissionModel e2m(Permission entity)
  {
    return thisMapper.d2m(baseMapper.toDto(entity));
  }


  @Override
  default List<PermissionModel> e2m(List<Permission> entityList)
  {
    return thisMapper.d2m(baseMapper.toDto(entityList));
  }


  @Override
  default Permission d2e(PermissionDTO dto)
  {
    return baseMapper.toEntity(dto);
  }


  @Override
  default List<Permission> d2e(List<PermissionDTO> dtoList)
  {
    return baseMapper.toEntity(dtoList);
  }


  @Override
  default PermissionDTO e2d(Permission entity)
  {
    return baseMapper.toDto(entity);
  }


  @Override
  default List<PermissionDTO> e2d(List<Permission> entityList)
  {
    return baseMapper.toDto(entityList);
  }
}
