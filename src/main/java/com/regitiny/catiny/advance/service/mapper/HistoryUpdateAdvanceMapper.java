package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.HistoryUpdateModel;
import com.regitiny.catiny.domain.HistoryUpdate;
import com.regitiny.catiny.service.dto.HistoryUpdateDTO;
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
public interface HistoryUpdateAdvanceMapper extends EntityAdvanceMapper<HistoryUpdateModel, HistoryUpdateDTO, HistoryUpdate>
{
  HistoryUpdateDTO request2d(HistoryUpdateModel.Request request);


  List<HistoryUpdateDTO> request2d(List<HistoryUpdateModel.Request> request);


  HistoryUpdateModel.Response d2Response(HistoryUpdateDTO dto);


  List<HistoryUpdateModel.Response> d2Response(List<HistoryUpdateDTO> dto);
}
