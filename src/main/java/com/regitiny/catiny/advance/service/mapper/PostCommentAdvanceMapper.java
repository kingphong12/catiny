package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.PostCommentModel;
import com.regitiny.catiny.domain.PostComment;
import com.regitiny.catiny.service.dto.PostCommentDTO;
import com.regitiny.catiny.service.mapper.PostCommentMapper;
import com.regitiny.catiny.service.mapper.PostCommentMapperImpl;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface PostCommentAdvanceMapper extends EntityAdvanceMapper<PostCommentModel, PostCommentDTO, PostComment>
{
  PostCommentMapper baseMapper = new PostCommentMapperImpl();

  PostCommentAdvanceMapper thisMapper = new PostCommentAdvanceMapperImpl();


  PostCommentDTO request2d(PostCommentModel.Request request);


  List<PostCommentDTO> request2d(List<PostCommentModel.Request> request);


  PostCommentModel.Response d2Response(PostCommentDTO dto);


  List<PostCommentModel.Response> d2Response(List<PostCommentDTO> dto);


  @Override
  default PostCommentModel e2m(PostComment entity)
  {
    return thisMapper.d2m(baseMapper.toDto(entity));
  }


  @Override
  default List<PostCommentModel> e2m(List<PostComment> entityList)
  {
    return thisMapper.d2m(baseMapper.toDto(entityList));
  }


  @Override
  default PostComment d2e(PostCommentDTO dto)
  {
    return baseMapper.toEntity(dto);
  }


  @Override
  default List<PostComment> d2e(List<PostCommentDTO> dtoList)
  {
    return baseMapper.toEntity(dtoList);
  }


  @Override
  default PostCommentDTO e2d(PostComment entity)
  {
    return baseMapper.toDto(entity);
  }


  @Override
  default List<PostCommentDTO> e2d(List<PostComment> entityList)
  {
    return baseMapper.toDto(entityList);
  }
}
