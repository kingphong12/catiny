package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.ImageModel;
import com.regitiny.catiny.domain.Image;
import com.regitiny.catiny.service.dto.ImageDTO;
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
public interface ImageAdvanceMapper extends EntityAdvanceMapper<ImageModel, ImageDTO, Image>
{
  ImageDTO request2d(ImageModel.Request request);


  List<ImageDTO> request2d(List<ImageModel.Request> request);


  ImageModel.Response d2Response(ImageDTO dto);


  List<ImageModel.Response> d2Response(List<ImageDTO> dto);
}
