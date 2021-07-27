package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.FileInfoModel;
import com.regitiny.catiny.domain.FileInfo;
import com.regitiny.catiny.service.dto.FileInfoDTO;
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
public interface FileInfoAdvanceMapper extends EntityAdvanceMapper<FileInfoModel, FileInfoDTO, FileInfo>
{
  FileInfoDTO request2d(FileInfoModel.Request request);


  List<FileInfoDTO> request2d(List<FileInfoModel.Request> request);


  FileInfoModel.Response d2Response(FileInfoDTO dto);


  List<FileInfoModel.Response> d2Response(List<FileInfoDTO> dto);
}
