package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.RankGroupModel;
import com.regitiny.catiny.domain.RankGroup;
import com.regitiny.catiny.service.dto.RankGroupDTO;
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
public interface RankGroupAdvanceMapper extends EntityAdvanceMapper<RankGroupModel, RankGroupDTO, RankGroup>
{
  RankGroupDTO request2d(RankGroupModel.Request request);


  List<RankGroupDTO> request2d(List<RankGroupModel.Request> request);


  RankGroupModel.Response d2Response(RankGroupDTO dto);


  List<RankGroupModel.Response> d2Response(List<RankGroupDTO> dto);
}
