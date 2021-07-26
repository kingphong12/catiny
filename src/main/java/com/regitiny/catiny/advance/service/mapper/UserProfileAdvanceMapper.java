package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.UserProfileModel;
import com.regitiny.catiny.domain.UserProfile;
import com.regitiny.catiny.service.dto.UserProfileDTO;
import com.regitiny.catiny.service.mapper.UserProfileMapper;
import com.regitiny.catiny.service.mapper.UserProfileMapperImpl;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface UserProfileAdvanceMapper extends EntityAdvanceMapper<UserProfileModel, UserProfileDTO, UserProfile>
{
  UserProfileMapper baseMapper = new UserProfileMapperImpl();

  UserProfileAdvanceMapper thisMapper = new UserProfileAdvanceMapperImpl();


  UserProfileDTO request2d(UserProfileModel.Request request);


  List<UserProfileDTO> request2d(List<UserProfileModel.Request> request);


  UserProfileModel.Response d2Response(UserProfileDTO dto);


  List<UserProfileModel.Response> d2Response(List<UserProfileDTO> dto);


  @Override
  default UserProfileModel e2m(UserProfile entity)
  {
    return thisMapper.d2m(baseMapper.toDto(entity));
  }


  @Override
  default List<UserProfileModel> e2m(List<UserProfile> entityList)
  {
    return thisMapper.d2m(baseMapper.toDto(entityList));
  }


  @Override
  default UserProfile d2e(UserProfileDTO dto)
  {
    return baseMapper.toEntity(dto);
  }


  @Override
  default List<UserProfile> d2e(List<UserProfileDTO> dtoList)
  {
    return baseMapper.toEntity(dtoList);
  }


  @Override
  default UserProfileDTO e2d(UserProfile entity)
  {
    return baseMapper.toDto(entity);
  }


  @Override
  default List<UserProfileDTO> e2d(List<UserProfile> entityList)
  {
    return baseMapper.toDto(entityList);
  }
}
