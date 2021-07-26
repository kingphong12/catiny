package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.FollowUserModel;
import com.regitiny.catiny.domain.FollowUser;
import com.regitiny.catiny.service.dto.FollowUserDTO;
import com.regitiny.catiny.service.mapper.FollowUserMapper;
import com.regitiny.catiny.service.mapper.FollowUserMapperImpl;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface FollowUserAdvanceMapper extends EntityAdvanceMapper<FollowUserModel, FollowUserDTO, FollowUser>
{
  FollowUserMapper baseMapper = new FollowUserMapperImpl();

  FollowUserAdvanceMapper thisMapper = new FollowUserAdvanceMapperImpl();


  FollowUserDTO request2d(FollowUserModel.Request request);


  List<FollowUserDTO> request2d(List<FollowUserModel.Request> request);


  FollowUserModel.Response d2Response(FollowUserDTO dto);


  List<FollowUserModel.Response> d2Response(List<FollowUserDTO> dto);


  @Override
  default FollowUserModel e2m(FollowUser entity)
  {
    return thisMapper.d2m(baseMapper.toDto(entity));
  }


  @Override
  default List<FollowUserModel> e2m(List<FollowUser> entityList)
  {
    return thisMapper.d2m(baseMapper.toDto(entityList));
  }


  @Override
  default FollowUser d2e(FollowUserDTO dto)
  {
    return baseMapper.toEntity(dto);
  }


  @Override
  default List<FollowUser> d2e(List<FollowUserDTO> dtoList)
  {
    return baseMapper.toEntity(dtoList);
  }


  @Override
  default FollowUserDTO e2d(FollowUser entity)
  {
    return baseMapper.toDto(entity);
  }


  @Override
  default List<FollowUserDTO> e2d(List<FollowUser> entityList)
  {
    return baseMapper.toDto(entityList);
  }
}
