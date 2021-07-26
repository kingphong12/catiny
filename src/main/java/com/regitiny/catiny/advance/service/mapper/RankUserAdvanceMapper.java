package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.RankUserModel;
import com.regitiny.catiny.domain.RankUser;
import com.regitiny.catiny.service.dto.RankUserDTO;
import com.regitiny.catiny.service.mapper.RankUserMapper;
import com.regitiny.catiny.service.mapper.RankUserMapperImpl;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface RankUserAdvanceMapper extends EntityAdvanceMapper<RankUserModel, RankUserDTO, RankUser>
{
  RankUserMapper baseMapper = new RankUserMapperImpl();

  RankUserAdvanceMapper thisMapper = new RankUserAdvanceMapperImpl();


  RankUserDTO request2d(RankUserModel.Request request);


  List<RankUserDTO> request2d(List<RankUserModel.Request> request);


  RankUserModel.Response d2Response(RankUserDTO dto);


  List<RankUserModel.Response> d2Response(List<RankUserDTO> dto);


  @Override
  default RankUserModel e2m(RankUser entity)
  {
    return thisMapper.d2m(baseMapper.toDto(entity));
  }


  @Override
  default List<RankUserModel> e2m(List<RankUser> entityList)
  {
    return thisMapper.d2m(baseMapper.toDto(entityList));
  }


  @Override
  default RankUser d2e(RankUserDTO dto)
  {
    return baseMapper.toEntity(dto);
  }


  @Override
  default List<RankUser> d2e(List<RankUserDTO> dtoList)
  {
    return baseMapper.toEntity(dtoList);
  }


  @Override
  default RankUserDTO e2d(RankUser entity)
  {
    return baseMapper.toDto(entity);
  }


  @Override
  default List<RankUserDTO> e2d(List<RankUser> entityList)
  {
    return baseMapper.toDto(entityList);
  }
}
