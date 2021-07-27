package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.BaseInfoModel;
import com.regitiny.catiny.domain.BaseInfo;
import com.regitiny.catiny.service.dto.BaseInfoDTO;
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
public interface BaseInfoAdvanceMapper extends EntityAdvanceMapper<BaseInfoModel, BaseInfoDTO, BaseInfo>
{
  BaseInfoDTO request2d(BaseInfoModel.Request request);


  List<BaseInfoDTO> request2d(List<BaseInfoModel.Request> request);


  BaseInfoModel.Response d2Response(BaseInfoDTO dto);


  List<BaseInfoModel.Response> d2Response(List<BaseInfoDTO> dto);
}
