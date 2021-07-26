package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.FileInfoModel;
import com.regitiny.catiny.domain.FileInfo;
import com.regitiny.catiny.service.dto.FileInfoDTO;
import com.regitiny.catiny.service.mapper.FileInfoMapper;
import com.regitiny.catiny.service.mapper.FileInfoMapperImpl;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface FileInfoAdvanceMapper extends EntityAdvanceMapper<FileInfoModel, FileInfoDTO, FileInfo>
{
  FileInfoMapper baseMapper = new FileInfoMapperImpl();

  FileInfoAdvanceMapper thisMapper = new FileInfoAdvanceMapperImpl();


  FileInfoDTO request2d(FileInfoModel.Request request);


  List<FileInfoDTO> request2d(List<FileInfoModel.Request> request);


  FileInfoModel.Response d2Response(FileInfoDTO dto);


  List<FileInfoModel.Response> d2Response(List<FileInfoDTO> dto);


  @Override
  default FileInfoModel e2m(FileInfo entity)
  {
    return thisMapper.d2m(baseMapper.toDto(entity));
  }


  @Override
  default List<FileInfoModel> e2m(List<FileInfo> entityList)
  {
    return thisMapper.d2m(baseMapper.toDto(entityList));
  }


  @Override
  default FileInfo d2e(FileInfoDTO dto)
  {
    return baseMapper.toEntity(dto);
  }


  @Override
  default List<FileInfo> d2e(List<FileInfoDTO> dtoList)
  {
    return baseMapper.toEntity(dtoList);
  }


  @Override
  default FileInfoDTO e2d(FileInfo entity)
  {
    return baseMapper.toDto(entity);
  }


  @Override
  default List<FileInfoDTO> e2d(List<FileInfo> entityList)
  {
    return baseMapper.toDto(entityList);
  }
}
