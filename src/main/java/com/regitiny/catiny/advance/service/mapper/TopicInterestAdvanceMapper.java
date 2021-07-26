package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.TopicInterestModel;
import com.regitiny.catiny.domain.TopicInterest;
import com.regitiny.catiny.service.dto.TopicInterestDTO;
import com.regitiny.catiny.service.mapper.TopicInterestMapper;
import com.regitiny.catiny.service.mapper.TopicInterestMapperImpl;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface TopicInterestAdvanceMapper extends EntityAdvanceMapper<TopicInterestModel, TopicInterestDTO, TopicInterest>
{
  TopicInterestMapper baseMapper = new TopicInterestMapperImpl();

  TopicInterestAdvanceMapper thisMapper = new TopicInterestAdvanceMapperImpl();


  TopicInterestDTO request2d(TopicInterestModel.Request request);


  List<TopicInterestDTO> request2d(List<TopicInterestModel.Request> request);


  TopicInterestModel.Response d2Response(TopicInterestDTO dto);


  List<TopicInterestModel.Response> d2Response(List<TopicInterestDTO> dto);


  @Override
  default TopicInterestModel e2m(TopicInterest entity)
  {
    return thisMapper.d2m(baseMapper.toDto(entity));
  }


  @Override
  default List<TopicInterestModel> e2m(List<TopicInterest> entityList)
  {
    return thisMapper.d2m(baseMapper.toDto(entityList));
  }


  @Override
  default TopicInterest d2e(TopicInterestDTO dto)
  {
    return baseMapper.toEntity(dto);
  }


  @Override
  default List<TopicInterest> d2e(List<TopicInterestDTO> dtoList)
  {
    return baseMapper.toEntity(dtoList);
  }


  @Override
  default TopicInterestDTO e2d(TopicInterest entity)
  {
    return baseMapper.toDto(entity);
  }


  @Override
  default List<TopicInterestDTO> e2d(List<TopicInterest> entityList)
  {
    return baseMapper.toDto(entityList);
  }
}
