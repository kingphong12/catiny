package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.HistoryUpdateModel;
import com.regitiny.catiny.domain.HistoryUpdate;
import com.regitiny.catiny.service.dto.HistoryUpdateDTO;
import com.regitiny.catiny.service.mapper.HistoryUpdateMapper;
import com.regitiny.catiny.service.mapper.HistoryUpdateMapperImpl;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface HistoryUpdateAdvanceMapper extends EntityAdvanceMapper<HistoryUpdateModel, HistoryUpdateDTO, HistoryUpdate>
{
  HistoryUpdateMapper baseMapper = new HistoryUpdateMapperImpl();

  HistoryUpdateAdvanceMapper thisMapper = new HistoryUpdateAdvanceMapperImpl();


  HistoryUpdateDTO request2d(HistoryUpdateModel.Request request);


  List<HistoryUpdateDTO> request2d(List<HistoryUpdateModel.Request> request);


  HistoryUpdateModel.Response d2Response(HistoryUpdateDTO dto);


  List<HistoryUpdateModel.Response> d2Response(List<HistoryUpdateDTO> dto);


  @Override
  default HistoryUpdateModel e2m(HistoryUpdate entity)
  {
    return thisMapper.d2m(baseMapper.toDto(entity));
  }


  @Override
  default List<HistoryUpdateModel> e2m(List<HistoryUpdate> entityList)
  {
    return thisMapper.d2m(baseMapper.toDto(entityList));
  }


  @Override
  default HistoryUpdate d2e(HistoryUpdateDTO dto)
  {
    return baseMapper.toEntity(dto);
  }


  @Override
  default List<HistoryUpdate> d2e(List<HistoryUpdateDTO> dtoList)
  {
    return baseMapper.toEntity(dtoList);
  }


  @Override
  default HistoryUpdateDTO e2d(HistoryUpdate entity)
  {
    return baseMapper.toDto(entity);
  }


  @Override
  default List<HistoryUpdateDTO> e2d(List<HistoryUpdate> entityList)
  {
    return baseMapper.toDto(entityList);
  }
}
