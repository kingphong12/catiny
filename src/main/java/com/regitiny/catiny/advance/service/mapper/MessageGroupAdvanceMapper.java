package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.MessageGroupModel;
import com.regitiny.catiny.domain.MessageGroup;
import com.regitiny.catiny.service.dto.MessageGroupDTO;
import com.regitiny.catiny.service.mapper.MessageGroupMapper;
import com.regitiny.catiny.service.mapper.MessageGroupMapperImpl;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface MessageGroupAdvanceMapper extends EntityAdvanceMapper<MessageGroupModel, MessageGroupDTO, MessageGroup>
{
  MessageGroupMapper baseMapper = new MessageGroupMapperImpl();

  MessageGroupAdvanceMapper thisMapper = new MessageGroupAdvanceMapperImpl();


  MessageGroupDTO request2d(MessageGroupModel.Request request);


  List<MessageGroupDTO> request2d(List<MessageGroupModel.Request> request);


  MessageGroupModel.Response d2Response(MessageGroupDTO dto);


  List<MessageGroupModel.Response> d2Response(List<MessageGroupDTO> dto);


  @Override
  default MessageGroupModel e2m(MessageGroup entity)
  {
    return thisMapper.d2m(baseMapper.toDto(entity));
  }


  @Override
  default List<MessageGroupModel> e2m(List<MessageGroup> entityList)
  {
    return thisMapper.d2m(baseMapper.toDto(entityList));
  }


  @Override
  default MessageGroup d2e(MessageGroupDTO dto)
  {
    return baseMapper.toEntity(dto);
  }


  @Override
  default List<MessageGroup> d2e(List<MessageGroupDTO> dtoList)
  {
    return baseMapper.toEntity(dtoList);
  }


  @Override
  default MessageGroupDTO e2d(MessageGroup entity)
  {
    return baseMapper.toDto(entity);
  }


  @Override
  default List<MessageGroupDTO> e2d(List<MessageGroup> entityList)
  {
    return baseMapper.toDto(entityList);
  }
}
