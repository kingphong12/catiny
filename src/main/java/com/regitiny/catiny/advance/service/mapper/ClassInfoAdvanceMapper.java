package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.ClassInfoModel;
import com.regitiny.catiny.domain.ClassInfo;
import com.regitiny.catiny.service.dto.ClassInfoDTO;
import com.regitiny.catiny.service.mapper.ClassInfoMapper;
import com.regitiny.catiny.service.mapper.ClassInfoMapperImpl;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface ClassInfoAdvanceMapper extends EntityAdvanceMapper<ClassInfoModel, ClassInfoDTO, ClassInfo>
{
  ClassInfoMapper baseMapper = new ClassInfoMapperImpl();

  ClassInfoAdvanceMapper thisMapper = new ClassInfoAdvanceMapperImpl();


  ClassInfoDTO request2d(ClassInfoModel.Request request);


  List<ClassInfoDTO> request2d(List<ClassInfoModel.Request> request);


  ClassInfoModel.Response d2Response(ClassInfoDTO dto);


  List<ClassInfoModel.Response> d2Response(List<ClassInfoDTO> dto);


  @Override
  default ClassInfoModel e2m(ClassInfo entity)
  {
    return thisMapper.d2m(baseMapper.toDto(entity));
  }


  @Override
  default List<ClassInfoModel> e2m(List<ClassInfo> entityList)
  {
    return thisMapper.d2m(baseMapper.toDto(entityList));
  }


  @Override
  default ClassInfo d2e(ClassInfoDTO dto)
  {
    return baseMapper.toEntity(dto);
  }


  @Override
  default List<ClassInfo> d2e(List<ClassInfoDTO> dtoList)
  {
    return baseMapper.toEntity(dtoList);
  }


  @Override
  default ClassInfoDTO e2d(ClassInfo entity)
  {
    return baseMapper.toDto(entity);
  }


  @Override
  default List<ClassInfoDTO> e2d(List<ClassInfo> entityList)
  {
    return baseMapper.toDto(entityList);
  }
}
