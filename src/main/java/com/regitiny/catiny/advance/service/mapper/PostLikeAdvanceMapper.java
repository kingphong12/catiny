package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.PostLikeModel;
import com.regitiny.catiny.domain.PostLike;
import com.regitiny.catiny.service.dto.PostLikeDTO;
import com.regitiny.catiny.service.mapper.PostLikeMapper;
import com.regitiny.catiny.service.mapper.PostLikeMapperImpl;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface PostLikeAdvanceMapper extends EntityAdvanceMapper<PostLikeModel, PostLikeDTO, PostLike>
{
  PostLikeMapper baseMapper = new PostLikeMapperImpl();

  PostLikeAdvanceMapper thisMapper = new PostLikeAdvanceMapperImpl();


  PostLikeDTO request2d(PostLikeModel.Request request);


  List<PostLikeDTO> request2d(List<PostLikeModel.Request> request);


  PostLikeModel.Response d2Response(PostLikeDTO dto);


  List<PostLikeModel.Response> d2Response(List<PostLikeDTO> dto);


  @Override
  default PostLikeModel e2m(PostLike entity)
  {
    return thisMapper.d2m(baseMapper.toDto(entity));
  }


  @Override
  default List<PostLikeModel> e2m(List<PostLike> entityList)
  {
    return thisMapper.d2m(baseMapper.toDto(entityList));
  }


  @Override
  default PostLike d2e(PostLikeDTO dto)
  {
    return baseMapper.toEntity(dto);
  }


  @Override
  default List<PostLike> d2e(List<PostLikeDTO> dtoList)
  {
    return baseMapper.toEntity(dtoList);
  }


  @Override
  default PostLikeDTO e2d(PostLike entity)
  {
    return baseMapper.toDto(entity);
  }


  @Override
  default List<PostLikeDTO> e2d(List<PostLike> entityList)
  {
    return baseMapper.toDto(entityList);
  }
}
