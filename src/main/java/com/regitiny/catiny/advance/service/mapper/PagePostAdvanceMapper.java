package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.PagePostModel;
import com.regitiny.catiny.domain.PagePost;
import com.regitiny.catiny.service.dto.PagePostDTO;
import com.regitiny.catiny.service.mapper.PagePostMapper;
import com.regitiny.catiny.service.mapper.PagePostMapperImpl;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface PagePostAdvanceMapper extends EntityAdvanceMapper<PagePostModel, PagePostDTO, PagePost>
{
  PagePostMapper baseMapper = new PagePostMapperImpl();

  PagePostAdvanceMapper thisMapper = new PagePostAdvanceMapperImpl();


  PagePostDTO request2d(PagePostModel.Request request);


  List<PagePostDTO> request2d(List<PagePostModel.Request> request);


  PagePostModel.Response d2Response(PagePostDTO dto);


  List<PagePostModel.Response> d2Response(List<PagePostDTO> dto);


  @Override
  default PagePostModel e2m(PagePost entity)
  {
    return thisMapper.d2m(baseMapper.toDto(entity));
  }


  @Override
  default List<PagePostModel> e2m(List<PagePost> entityList)
  {
    return thisMapper.d2m(baseMapper.toDto(entityList));
  }


  @Override
  default PagePost d2e(PagePostDTO dto)
  {
    return baseMapper.toEntity(dto);
  }


  @Override
  default List<PagePost> d2e(List<PagePostDTO> dtoList)
  {
    return baseMapper.toEntity(dtoList);
  }


  @Override
  default PagePostDTO e2d(PagePost entity)
  {
    return baseMapper.toDto(entity);
  }


  @Override
  default List<PagePostDTO> e2d(List<PagePost> entityList)
  {
    return baseMapper.toDto(entityList);
  }
}
