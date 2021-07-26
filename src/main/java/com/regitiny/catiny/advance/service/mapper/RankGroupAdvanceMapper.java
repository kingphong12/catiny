package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.RankGroupModel;
import com.regitiny.catiny.domain.RankGroup;
import com.regitiny.catiny.service.dto.RankGroupDTO;
import com.regitiny.catiny.service.mapper.RankGroupMapper;
import com.regitiny.catiny.service.mapper.RankGroupMapperImpl;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface RankGroupAdvanceMapper extends EntityAdvanceMapper<RankGroupModel, RankGroupDTO, RankGroup>
{
  RankGroupMapper baseMapper = new RankGroupMapperImpl();

  RankGroupAdvanceMapper thisMapper = new RankGroupAdvanceMapperImpl();


  RankGroupDTO request2d(RankGroupModel.Request request);


  List<RankGroupDTO> request2d(List<RankGroupModel.Request> request);


  RankGroupModel.Response d2Response(RankGroupDTO dto);


  List<RankGroupModel.Response> d2Response(List<RankGroupDTO> dto);


  @Override
  default RankGroupModel e2m(RankGroup entity)
  {
    return thisMapper.d2m(baseMapper.toDto(entity));
  }


  @Override
  default List<RankGroupModel> e2m(List<RankGroup> entityList)
  {
    return thisMapper.d2m(baseMapper.toDto(entityList));
  }


  @Override
  default RankGroup d2e(RankGroupDTO dto)
  {
    return baseMapper.toEntity(dto);
  }


  @Override
  default List<RankGroup> d2e(List<RankGroupDTO> dtoList)
  {
    return baseMapper.toEntity(dtoList);
  }


  @Override
  default RankGroupDTO e2d(RankGroup entity)
  {
    return baseMapper.toDto(entity);
  }


  @Override
  default List<RankGroupDTO> e2d(List<RankGroup> entityList)
  {
    return baseMapper.toDto(entityList);
  }
}
