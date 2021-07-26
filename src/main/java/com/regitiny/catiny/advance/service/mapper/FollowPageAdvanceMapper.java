package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.FollowPageModel;
import com.regitiny.catiny.domain.FollowPage;
import com.regitiny.catiny.service.dto.FollowPageDTO;
import com.regitiny.catiny.service.mapper.FollowPageMapper;
import com.regitiny.catiny.service.mapper.FollowPageMapperImpl;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface FollowPageAdvanceMapper extends EntityAdvanceMapper<FollowPageModel, FollowPageDTO, FollowPage>
{
  FollowPageMapper baseMapper = new FollowPageMapperImpl();

  FollowPageAdvanceMapper thisMapper = new FollowPageAdvanceMapperImpl();


  FollowPageDTO request2d(FollowPageModel.Request request);


  List<FollowPageDTO> request2d(List<FollowPageModel.Request> request);


  FollowPageModel.Response d2Response(FollowPageDTO dto);


  List<FollowPageModel.Response> d2Response(List<FollowPageDTO> dto);


  @Override
  default FollowPageModel e2m(FollowPage entity)
  {
    return thisMapper.d2m(baseMapper.toDto(entity));
  }


  @Override
  default List<FollowPageModel> e2m(List<FollowPage> entityList)
  {
    return thisMapper.d2m(baseMapper.toDto(entityList));
  }


  @Override
  default FollowPage d2e(FollowPageDTO dto)
  {
    return baseMapper.toEntity(dto);
  }


  @Override
  default List<FollowPage> d2e(List<FollowPageDTO> dtoList)
  {
    return baseMapper.toEntity(dtoList);
  }


  @Override
  default FollowPageDTO e2d(FollowPage entity)
  {
    return baseMapper.toDto(entity);
  }


  @Override
  default List<FollowPageDTO> e2d(List<FollowPage> entityList)
  {
    return baseMapper.toDto(entityList);
  }
}
