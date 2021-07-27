package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.NewsFeedModel;
import com.regitiny.catiny.domain.NewsFeed;
import com.regitiny.catiny.service.dto.NewsFeedDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * this is a custom mapper for each entity .
 * this mapper extend from Jhipster mapper
 * ( d=dto, e=entity , m=model and To=2 ) --> ( dtoToEntity = d2e , modelToDto = m2d ).
 */
@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface NewsFeedAdvanceMapper extends EntityAdvanceMapper<NewsFeedModel, NewsFeedDTO, NewsFeed>
{
  NewsFeedDTO request2d(NewsFeedModel.Request request);


  List<NewsFeedDTO> request2d(List<NewsFeedModel.Request> request);


  NewsFeedModel.Response d2Response(NewsFeedDTO dto);


  List<NewsFeedModel.Response> d2Response(List<NewsFeedDTO> dto);
}
