package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.RankUserModel;
import com.regitiny.catiny.domain.RankUser;
import com.regitiny.catiny.service.dto.RankUserDTO;
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
public interface RankUserAdvanceMapper extends EntityAdvanceMapper<RankUserModel, RankUserDTO, RankUser>
{
  RankUserDTO request2d(RankUserModel.Request request);


  List<RankUserDTO> request2d(List<RankUserModel.Request> request);


  RankUserModel.Response d2Response(RankUserDTO dto);


  List<RankUserModel.Response> d2Response(List<RankUserDTO> dto);
}
