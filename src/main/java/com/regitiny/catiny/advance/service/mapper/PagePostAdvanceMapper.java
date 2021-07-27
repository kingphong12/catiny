package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.PagePostModel;
import com.regitiny.catiny.domain.PagePost;
import com.regitiny.catiny.service.dto.PagePostDTO;
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
public interface PagePostAdvanceMapper extends EntityAdvanceMapper<PagePostModel, PagePostDTO, PagePost>
{
  PagePostDTO request2d(PagePostModel.Request request);


  List<PagePostDTO> request2d(List<PagePostModel.Request> request);


  PagePostModel.Response d2Response(PagePostDTO dto);


  List<PagePostModel.Response> d2Response(List<PagePostDTO> dto);
}
