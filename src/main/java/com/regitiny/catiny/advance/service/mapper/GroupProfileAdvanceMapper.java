package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.GroupProfileModel;
import com.regitiny.catiny.domain.GroupProfile;
import com.regitiny.catiny.service.dto.GroupProfileDTO;
import com.regitiny.catiny.service.mapper.GroupProfileMapper;
import com.regitiny.catiny.service.mapper.GroupProfileMapperImpl;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface GroupProfileAdvanceMapper extends EntityAdvanceMapper<GroupProfileModel, GroupProfileDTO, GroupProfile>
{
  GroupProfileMapper baseMapper = new GroupProfileMapperImpl();

  GroupProfileAdvanceMapper thisMapper = new GroupProfileAdvanceMapperImpl();


  GroupProfileDTO request2d(GroupProfileModel.Request request);


  List<GroupProfileDTO> request2d(List<GroupProfileModel.Request> request);


  GroupProfileModel.Response d2Response(GroupProfileDTO dto);


  List<GroupProfileModel.Response> d2Response(List<GroupProfileDTO> dto);


  @Override
  default GroupProfileModel e2m(GroupProfile entity)
  {
    return thisMapper.d2m(baseMapper.toDto(entity));
  }


  @Override
  default List<GroupProfileModel> e2m(List<GroupProfile> entityList)
  {
    return thisMapper.d2m(baseMapper.toDto(entityList));
  }


  @Override
  default GroupProfile d2e(GroupProfileDTO dto)
  {
    return baseMapper.toEntity(dto);
  }


  @Override
  default List<GroupProfile> d2e(List<GroupProfileDTO> dtoList)
  {
    return baseMapper.toEntity(dtoList);
  }


  @Override
  default GroupProfileDTO e2d(GroupProfile entity)
  {
    return baseMapper.toDto(entity);
  }


  @Override
  default List<GroupProfileDTO> e2d(List<GroupProfile> entityList)
  {
    return baseMapper.toDto(entityList);
  }
}
