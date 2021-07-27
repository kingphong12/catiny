package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.HanhChinhVNModel;
import com.regitiny.catiny.domain.HanhChinhVN;
import com.regitiny.catiny.service.dto.HanhChinhVNDTO;
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
public interface HanhChinhVNAdvanceMapper extends EntityAdvanceMapper<HanhChinhVNModel, HanhChinhVNDTO, HanhChinhVN>
{
  HanhChinhVNDTO request2d(HanhChinhVNModel.Request request);


  List<HanhChinhVNDTO> request2d(List<HanhChinhVNModel.Request> request);


  HanhChinhVNModel.Response d2Response(HanhChinhVNDTO dto);


  List<HanhChinhVNModel.Response> d2Response(List<HanhChinhVNDTO> dto);
}
