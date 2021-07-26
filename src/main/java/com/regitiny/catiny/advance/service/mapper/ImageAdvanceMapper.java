package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.ImageModel;
import com.regitiny.catiny.domain.Image;
import com.regitiny.catiny.service.dto.ImageDTO;
import com.regitiny.catiny.service.mapper.ImageMapper;
import com.regitiny.catiny.service.mapper.ImageMapperImpl;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface ImageAdvanceMapper extends EntityAdvanceMapper<ImageModel, ImageDTO, Image>
{
  ImageMapper baseMapper = new ImageMapperImpl();

  ImageAdvanceMapper thisMapper = new ImageAdvanceMapperImpl();


  ImageDTO request2d(ImageModel.Request request);


  List<ImageDTO> request2d(List<ImageModel.Request> request);


  ImageModel.Response d2Response(ImageDTO dto);


  List<ImageModel.Response> d2Response(List<ImageDTO> dto);


  @Override
  default ImageModel e2m(Image entity)
  {
    return thisMapper.d2m(baseMapper.toDto(entity));
  }


  @Override
  default List<ImageModel> e2m(List<Image> entityList)
  {
    return thisMapper.d2m(baseMapper.toDto(entityList));
  }


  @Override
  default Image d2e(ImageDTO dto)
  {
    return baseMapper.toEntity(dto);
  }


  @Override
  default List<Image> d2e(List<ImageDTO> dtoList)
  {
    return baseMapper.toEntity(dtoList);
  }


  @Override
  default ImageDTO e2d(Image entity)
  {
    return baseMapper.toDto(entity);
  }


  @Override
  default List<ImageDTO> e2d(List<Image> entityList)
  {
    return baseMapper.toDto(entityList);
  }
}
