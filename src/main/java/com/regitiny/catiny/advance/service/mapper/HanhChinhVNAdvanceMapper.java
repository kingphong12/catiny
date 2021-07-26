package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.HanhChinhVNModel;
import com.regitiny.catiny.domain.HanhChinhVN;
import com.regitiny.catiny.service.dto.HanhChinhVNDTO;
import com.regitiny.catiny.service.mapper.HanhChinhVNMapper;
import com.regitiny.catiny.service.mapper.HanhChinhVNMapperImpl;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface HanhChinhVNAdvanceMapper extends EntityAdvanceMapper<HanhChinhVNModel, HanhChinhVNDTO, HanhChinhVN>
{
  HanhChinhVNMapper baseMapper = new HanhChinhVNMapperImpl();

  HanhChinhVNAdvanceMapper thisMapper = new HanhChinhVNAdvanceMapperImpl();


  HanhChinhVNDTO request2d(HanhChinhVNModel.Request request);


  List<HanhChinhVNDTO> request2d(List<HanhChinhVNModel.Request> request);


  HanhChinhVNModel.Response d2Response(HanhChinhVNDTO dto);


  List<HanhChinhVNModel.Response> d2Response(List<HanhChinhVNDTO> dto);


  @Override
  default HanhChinhVNModel e2m(HanhChinhVN entity)
  {
    return thisMapper.d2m(baseMapper.toDto(entity));
  }


  @Override
  default List<HanhChinhVNModel> e2m(List<HanhChinhVN> entityList)
  {
    return thisMapper.d2m(baseMapper.toDto(entityList));
  }


  @Override
  default HanhChinhVN d2e(HanhChinhVNDTO dto)
  {
    return baseMapper.toEntity(dto);
  }


  @Override
  default List<HanhChinhVN> d2e(List<HanhChinhVNDTO> dtoList)
  {
    return baseMapper.toEntity(dtoList);
  }


  @Override
  default HanhChinhVNDTO e2d(HanhChinhVN entity)
  {
    return baseMapper.toDto(entity);
  }


  @Override
  default List<HanhChinhVNDTO> e2d(List<HanhChinhVN> entityList)
  {
    return baseMapper.toDto(entityList);
  }
}
