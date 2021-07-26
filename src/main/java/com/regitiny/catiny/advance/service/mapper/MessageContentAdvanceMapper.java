package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.MessageContentModel;
import com.regitiny.catiny.domain.MessageContent;
import com.regitiny.catiny.service.dto.MessageContentDTO;
import com.regitiny.catiny.service.mapper.MessageContentMapper;
import com.regitiny.catiny.service.mapper.MessageContentMapperImpl;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface MessageContentAdvanceMapper extends EntityAdvanceMapper<MessageContentModel, MessageContentDTO, MessageContent>
{
  MessageContentMapper baseMapper = new MessageContentMapperImpl();

  MessageContentAdvanceMapper thisMapper = new MessageContentAdvanceMapperImpl();


  MessageContentDTO request2d(MessageContentModel.Request request);


  List<MessageContentDTO> request2d(List<MessageContentModel.Request> request);


  MessageContentModel.Response d2Response(MessageContentDTO dto);


  List<MessageContentModel.Response> d2Response(List<MessageContentDTO> dto);


  @Override
  default MessageContentModel e2m(MessageContent entity)
  {
    return thisMapper.d2m(baseMapper.toDto(entity));
  }


  @Override
  default List<MessageContentModel> e2m(List<MessageContent> entityList)
  {
    return thisMapper.d2m(baseMapper.toDto(entityList));
  }


  @Override
  default MessageContent d2e(MessageContentDTO dto)
  {
    return baseMapper.toEntity(dto);
  }


  @Override
  default List<MessageContent> d2e(List<MessageContentDTO> dtoList)
  {
    return baseMapper.toEntity(dtoList);
  }


  @Override
  default MessageContentDTO e2d(MessageContent entity)
  {
    return baseMapper.toDto(entity);
  }


  @Override
  default List<MessageContentDTO> e2d(List<MessageContent> entityList)
  {
    return baseMapper.toDto(entityList);
  }
}
