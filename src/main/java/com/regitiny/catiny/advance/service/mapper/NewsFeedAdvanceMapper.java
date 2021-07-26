package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.NewsFeedModel;
import com.regitiny.catiny.domain.NewsFeed;
import com.regitiny.catiny.service.dto.NewsFeedDTO;
import com.regitiny.catiny.service.mapper.NewsFeedMapper;
import com.regitiny.catiny.service.mapper.NewsFeedMapperImpl;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface NewsFeedAdvanceMapper extends EntityAdvanceMapper<NewsFeedModel, NewsFeedDTO, NewsFeed>
{
  NewsFeedMapper baseMapper = new NewsFeedMapperImpl();

  NewsFeedAdvanceMapper thisMapper = new NewsFeedAdvanceMapperImpl();


  NewsFeedDTO request2d(NewsFeedModel.Request request);


  List<NewsFeedDTO> request2d(List<NewsFeedModel.Request> request);


  NewsFeedModel.Response d2Response(NewsFeedDTO dto);


  List<NewsFeedModel.Response> d2Response(List<NewsFeedDTO> dto);


  @Override
  default NewsFeedModel e2m(NewsFeed entity)
  {
    return thisMapper.d2m(baseMapper.toDto(entity));
  }


  @Override
  default List<NewsFeedModel> e2m(List<NewsFeed> entityList)
  {
    return thisMapper.d2m(baseMapper.toDto(entityList));
  }


  @Override
  default NewsFeed d2e(NewsFeedDTO dto)
  {
    return baseMapper.toEntity(dto);
  }


  @Override
  default List<NewsFeed> d2e(List<NewsFeedDTO> dtoList)
  {
    return baseMapper.toEntity(dtoList);
  }


  @Override
  default NewsFeedDTO e2d(NewsFeed entity)
  {
    return baseMapper.toDto(entity);
  }


  @Override
  default List<NewsFeedDTO> e2d(List<NewsFeed> entityList)
  {
    return baseMapper.toDto(entityList);
  }
}
