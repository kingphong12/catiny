package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.PostModel;
import com.regitiny.catiny.domain.Post;
import com.regitiny.catiny.service.dto.PostDTO;
import com.regitiny.catiny.service.mapper.PostMapper;
import com.regitiny.catiny.service.mapper.PostMapperImpl;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface PostAdvanceMapper extends EntityAdvanceMapper<PostModel, PostDTO, Post>
{
  PostMapper baseMapper = new PostMapperImpl();

  PostAdvanceMapper thisMapper = new PostAdvanceMapperImpl();


  PostDTO request2d(PostModel.Request request);


  List<PostDTO> request2d(List<PostModel.Request> request);


  PostModel.Response d2Response(PostDTO dto);


  List<PostModel.Response> d2Response(List<PostDTO> dto);


  @Override
  default PostModel e2m(Post entity)
  {
    return thisMapper.d2m(baseMapper.toDto(entity));
  }


  @Override
  default List<PostModel> e2m(List<Post> entityList)
  {
    return thisMapper.d2m(baseMapper.toDto(entityList));
  }


  @Override
  default Post d2e(PostDTO dto)
  {
    return baseMapper.toEntity(dto);
  }


  @Override
  default List<Post> d2e(List<PostDTO> dtoList)
  {
    return baseMapper.toEntity(dtoList);
  }


  @Override
  default PostDTO e2d(Post entity)
  {
    return baseMapper.toDto(entity);
  }


  @Override
  default List<PostDTO> e2d(List<Post> entityList)
  {
    return baseMapper.toDto(entityList);
  }
}
