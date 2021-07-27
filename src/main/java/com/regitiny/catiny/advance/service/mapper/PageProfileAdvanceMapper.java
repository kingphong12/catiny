package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.PageProfileModel;
import com.regitiny.catiny.domain.PageProfile;
import com.regitiny.catiny.service.dto.PageProfileDTO;
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
public interface PageProfileAdvanceMapper extends EntityAdvanceMapper<PageProfileModel, PageProfileDTO, PageProfile>
{
  PageProfileDTO request2d(PageProfileModel.Request request);


  List<PageProfileDTO> request2d(List<PageProfileModel.Request> request);


  PageProfileModel.Response d2Response(PageProfileDTO dto);


  List<PageProfileModel.Response> d2Response(List<PageProfileDTO> dto);
}
