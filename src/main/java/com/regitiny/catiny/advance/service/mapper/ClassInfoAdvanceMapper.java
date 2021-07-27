package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.ClassInfoModel;
import com.regitiny.catiny.domain.ClassInfo;
import com.regitiny.catiny.service.dto.ClassInfoDTO;
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
public interface ClassInfoAdvanceMapper extends EntityAdvanceMapper<ClassInfoModel, ClassInfoDTO, ClassInfo>
{
  ClassInfoDTO request2d(ClassInfoModel.Request request);


  List<ClassInfoDTO> request2d(List<ClassInfoModel.Request> request);


  ClassInfoModel.Response d2Response(ClassInfoDTO dto);


  List<ClassInfoModel.Response> d2Response(List<ClassInfoDTO> dto);
}
