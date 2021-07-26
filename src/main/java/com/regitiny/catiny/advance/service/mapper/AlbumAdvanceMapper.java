package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.AlbumModel;
import com.regitiny.catiny.domain.Album;
import com.regitiny.catiny.service.dto.AlbumDTO;
import com.regitiny.catiny.service.mapper.AlbumMapper;
import com.regitiny.catiny.service.mapper.AlbumMapperImpl;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface AlbumAdvanceMapper extends EntityAdvanceMapper<AlbumModel, AlbumDTO, Album>
{
  AlbumMapper baseMapper = new AlbumMapperImpl();

  AlbumAdvanceMapper thisMapper = new AlbumAdvanceMapperImpl();


  AlbumDTO request2d(AlbumModel.Request request);


  List<AlbumDTO> request2d(List<AlbumModel.Request> request);


  AlbumModel.Response d2Response(AlbumDTO dto);


  List<AlbumModel.Response> d2Response(List<AlbumDTO> dto);


  @Override
  default AlbumModel e2m(Album entity)
  {
    return thisMapper.d2m(baseMapper.toDto(entity));
  }


  @Override
  default List<AlbumModel> e2m(List<Album> entityList)
  {
    return thisMapper.d2m(baseMapper.toDto(entityList));
  }


  @Override
  default Album d2e(AlbumDTO dto)
  {
    return baseMapper.toEntity(dto);
  }


  @Override
  default List<Album> d2e(List<AlbumDTO> dtoList)
  {
    return baseMapper.toEntity(dtoList);
  }


  @Override
  default AlbumDTO e2d(Album entity)
  {
    return baseMapper.toDto(entity);
  }


  @Override
  default List<AlbumDTO> e2d(List<Album> entityList)
  {
    return baseMapper.toDto(entityList);
  }
}
