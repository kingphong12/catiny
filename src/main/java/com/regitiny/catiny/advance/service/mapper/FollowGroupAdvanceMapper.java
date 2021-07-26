package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.FollowGroupModel;
import com.regitiny.catiny.domain.FollowGroup;
import com.regitiny.catiny.service.dto.FollowGroupDTO;
import com.regitiny.catiny.service.mapper.FollowGroupMapper;
import com.regitiny.catiny.service.mapper.FollowGroupMapperImpl;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface FollowGroupAdvanceMapper extends EntityAdvanceMapper<FollowGroupModel, FollowGroupDTO, FollowGroup>
{
  FollowGroupMapper baseMapper = new FollowGroupMapperImpl();

  FollowGroupAdvanceMapper thisMapper = new FollowGroupAdvanceMapperImpl();


  FollowGroupDTO request2d(FollowGroupModel.Request request);


  List<FollowGroupDTO> request2d(List<FollowGroupModel.Request> request);


  FollowGroupModel.Response d2Response(FollowGroupDTO dto);


  List<FollowGroupModel.Response> d2Response(List<FollowGroupDTO> dto);


  @Override
  default FollowGroupModel e2m(FollowGroup entity)
  {
    return thisMapper.d2m(baseMapper.toDto(entity));
  }


  @Override
  default List<FollowGroupModel> e2m(List<FollowGroup> entityList)
  {
    return thisMapper.d2m(baseMapper.toDto(entityList));
  }


  @Override
  default FollowGroup d2e(FollowGroupDTO dto)
  {
    return baseMapper.toEntity(dto);
  }


  @Override
  default List<FollowGroup> d2e(List<FollowGroupDTO> dtoList)
  {
    return baseMapper.toEntity(dtoList);
  }


  @Override
  default FollowGroupDTO e2d(FollowGroup entity)
  {
    return baseMapper.toDto(entity);
  }


  @Override
  default List<FollowGroupDTO> e2d(List<FollowGroup> entityList)
  {
    return baseMapper.toDto(entityList);
  }
}
