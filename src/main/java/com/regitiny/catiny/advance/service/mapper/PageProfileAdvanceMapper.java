package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.PageProfileModel;
import com.regitiny.catiny.domain.PageProfile;
import com.regitiny.catiny.service.dto.PageProfileDTO;
import com.regitiny.catiny.service.mapper.PageProfileMapper;
import com.regitiny.catiny.service.mapper.PageProfileMapperImpl;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface PageProfileAdvanceMapper extends EntityAdvanceMapper<PageProfileModel, PageProfileDTO, PageProfile>
{
  PageProfileMapper baseMapper = new PageProfileMapperImpl();

  PageProfileAdvanceMapper thisMapper = new PageProfileAdvanceMapperImpl();


  PageProfileDTO request2d(PageProfileModel.Request request);


  List<PageProfileDTO> request2d(List<PageProfileModel.Request> request);


  PageProfileModel.Response d2Response(PageProfileDTO dto);


  List<PageProfileModel.Response> d2Response(List<PageProfileDTO> dto);


  @Override
  default PageProfileModel e2m(PageProfile entity)
  {
    return thisMapper.d2m(baseMapper.toDto(entity));
  }


  @Override
  default List<PageProfileModel> e2m(List<PageProfile> entityList)
  {
    return thisMapper.d2m(baseMapper.toDto(entityList));
  }


  @Override
  default PageProfile d2e(PageProfileDTO dto)
  {
    return baseMapper.toEntity(dto);
  }


  @Override
  default List<PageProfile> d2e(List<PageProfileDTO> dtoList)
  {
    return baseMapper.toEntity(dtoList);
  }


  @Override
  default PageProfileDTO e2d(PageProfile entity)
  {
    return baseMapper.toDto(entity);
  }


  @Override
  default List<PageProfileDTO> e2d(List<PageProfile> entityList)
  {
    return baseMapper.toDto(entityList);
  }
}
