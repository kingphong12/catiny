package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.MasterUserModel;
import com.regitiny.catiny.domain.MasterUser;
import com.regitiny.catiny.service.dto.MasterUserDTO;
import com.regitiny.catiny.service.mapper.MasterUserMapper;
import com.regitiny.catiny.service.mapper.MasterUserMapperImpl;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface MasterUserAdvanceMapper extends EntityAdvanceMapper<MasterUserModel, MasterUserDTO, MasterUser>
{
  MasterUserMapper baseMapper = new MasterUserMapperImpl();

  MasterUserAdvanceMapper thisMapper = new MasterUserAdvanceMapperImpl();


  MasterUserDTO request2d(MasterUserModel.Request request);


  List<MasterUserDTO> request2d(List<MasterUserModel.Request> request);


  MasterUserModel.Response d2Response(MasterUserDTO dto);


  List<MasterUserModel.Response> d2Response(List<MasterUserDTO> dto);


  @Override
  default MasterUserModel e2m(MasterUser entity)
  {
    return thisMapper.d2m(baseMapper.toDto(entity));
  }


  @Override
  default List<MasterUserModel> e2m(List<MasterUser> entityList)
  {
    return thisMapper.d2m(baseMapper.toDto(entityList));
  }


  @Override
  default MasterUser d2e(MasterUserDTO dto)
  {
    return baseMapper.toEntity(dto);
  }


  @Override
  default List<MasterUser> d2e(List<MasterUserDTO> dtoList)
  {
    return baseMapper.toEntity(dtoList);
  }


  @Override
  default MasterUserDTO e2d(MasterUser entity)
  {
    return baseMapper.toDto(entity);
  }


  @Override
  default List<MasterUserDTO> e2d(List<MasterUser> entityList)
  {
    return baseMapper.toDto(entityList);
  }
}
