package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.BaseInfoModel;
import com.regitiny.catiny.domain.BaseInfo;
import com.regitiny.catiny.service.dto.BaseInfoDTO;
import com.regitiny.catiny.service.mapper.BaseInfoMapper;
import com.regitiny.catiny.service.mapper.BaseInfoMapperImpl;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface BaseInfoAdvanceMapper extends EntityAdvanceMapper<BaseInfoModel, BaseInfoDTO, BaseInfo>
{
  BaseInfoMapper baseMapper = new BaseInfoMapperImpl();

  BaseInfoAdvanceMapper thisMapper = new BaseInfoAdvanceMapperImpl();


  BaseInfoDTO request2d(BaseInfoModel.Request request);


  List<BaseInfoDTO> request2d(List<BaseInfoModel.Request> request);


  BaseInfoModel.Response d2Response(BaseInfoDTO dto);


  List<BaseInfoModel.Response> d2Response(List<BaseInfoDTO> dto);


  @Override
  default BaseInfoModel e2m(BaseInfo entity)
  {
    return thisMapper.d2m(baseMapper.toDto(entity));
  }


  @Override
  default List<BaseInfoModel> e2m(List<BaseInfo> entityList)
  {
    return thisMapper.d2m(baseMapper.toDto(entityList));
  }


  @Override
  default BaseInfo d2e(BaseInfoDTO dto)
  {
    return baseMapper.toEntity(dto);
  }


  @Override
  default List<BaseInfo> d2e(List<BaseInfoDTO> dtoList)
  {
    return baseMapper.toEntity(dtoList);
  }


  @Override
  default BaseInfoDTO e2d(BaseInfo entity)
  {
    return baseMapper.toDto(entity);
  }


  @Override
  default List<BaseInfoDTO> e2d(List<BaseInfo> entityList)
  {
    return baseMapper.toDto(entityList);
  }
}
