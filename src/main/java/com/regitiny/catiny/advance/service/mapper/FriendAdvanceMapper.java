package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.FriendModel;
import com.regitiny.catiny.domain.Friend;
import com.regitiny.catiny.service.dto.FriendDTO;
import com.regitiny.catiny.service.mapper.FriendMapper;
import com.regitiny.catiny.service.mapper.FriendMapperImpl;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface FriendAdvanceMapper extends EntityAdvanceMapper<FriendModel, FriendDTO, Friend>
{
  FriendMapper baseMapper = new FriendMapperImpl();

  FriendAdvanceMapper thisMapper = new FriendAdvanceMapperImpl();


  FriendDTO request2d(FriendModel.Request request);


  List<FriendDTO> request2d(List<FriendModel.Request> request);


  FriendModel.Response d2Response(FriendDTO dto);


  List<FriendModel.Response> d2Response(List<FriendDTO> dto);


  @Override
  default FriendModel e2m(Friend entity)
  {
    return thisMapper.d2m(baseMapper.toDto(entity));
  }


  @Override
  default List<FriendModel> e2m(List<Friend> entityList)
  {
    return thisMapper.d2m(baseMapper.toDto(entityList));
  }


  @Override
  default Friend d2e(FriendDTO dto)
  {
    return baseMapper.toEntity(dto);
  }


  @Override
  default List<Friend> d2e(List<FriendDTO> dtoList)
  {
    return baseMapper.toEntity(dtoList);
  }


  @Override
  default FriendDTO e2d(Friend entity)
  {
    return baseMapper.toDto(entity);
  }


  @Override
  default List<FriendDTO> e2d(List<Friend> entityList)
  {
    return baseMapper.toDto(entityList);
  }
}
