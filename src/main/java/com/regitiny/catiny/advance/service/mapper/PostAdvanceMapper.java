package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.PostModel;
import com.regitiny.catiny.domain.Post;
import com.regitiny.catiny.service.dto.PostDTO;
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
public interface PostAdvanceMapper extends EntityAdvanceMapper<PostModel, PostDTO, Post>
{
  PostDTO request2d(PostModel.Request request);


  List<PostDTO> request2d(List<PostModel.Request> request);


  PostModel.Response d2Response(PostDTO dto);


  List<PostModel.Response> d2Response(List<PostDTO> dto);
}
