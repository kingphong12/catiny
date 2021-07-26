package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.GroupPostModel;
import com.regitiny.catiny.domain.GroupPost;
import com.regitiny.catiny.service.dto.GroupPostDTO;
import com.regitiny.catiny.service.mapper.GroupPostMapper;
import com.regitiny.catiny.service.mapper.GroupPostMapperImpl;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface GroupPostAdvanceMapper extends EntityAdvanceMapper<GroupPostModel, GroupPostDTO, GroupPost>
{
  GroupPostMapper baseMapper = new GroupPostMapperImpl();

  GroupPostAdvanceMapper thisMapper = new GroupPostAdvanceMapperImpl();


  GroupPostDTO request2d(GroupPostModel.Request request);


  List<GroupPostDTO> request2d(List<GroupPostModel.Request> request);


  GroupPostModel.Response d2Response(GroupPostDTO dto);


  List<GroupPostModel.Response> d2Response(List<GroupPostDTO> dto);


  @Override
  default GroupPostModel e2m(GroupPost entity)
  {
    return thisMapper.d2m(baseMapper.toDto(entity));
  }


  @Override
  default List<GroupPostModel> e2m(List<GroupPost> entityList)
  {
    return thisMapper.d2m(baseMapper.toDto(entityList));
  }


  @Override
  default GroupPost d2e(GroupPostDTO dto)
  {
    return baseMapper.toEntity(dto);
  }


  @Override
  default List<GroupPost> d2e(List<GroupPostDTO> dtoList)
  {
    return baseMapper.toEntity(dtoList);
  }


  @Override
  default GroupPostDTO e2d(GroupPost entity)
  {
    return baseMapper.toDto(entity);
  }


  @Override
  default List<GroupPostDTO> e2d(List<GroupPost> entityList)
  {
    return baseMapper.toDto(entityList);
  }
}
