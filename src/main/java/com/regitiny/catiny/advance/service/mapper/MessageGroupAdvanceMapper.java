package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.MessageGroupModel;
import com.regitiny.catiny.domain.MessageGroup;
import com.regitiny.catiny.service.dto.MessageGroupDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * this is a custom mapper for each entity .
 * this mapper extend from Jhipster mapper
 * ( d=dto, e=entity , m=model and To=2 ) --> ( dtoToEntity = d2e , modelToDto = m2d ).
 */
@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface MessageGroupAdvanceMapper extends EntityAdvanceMapper<MessageGroupModel, MessageGroupDTO, MessageGroup>
{
  MessageGroupDTO request2d(MessageGroupModel.Request request);


  List<MessageGroupDTO> request2d(List<MessageGroupModel.Request> request);


  MessageGroupModel.Response d2Response(MessageGroupDTO dto);


  List<MessageGroupModel.Response> d2Response(List<MessageGroupDTO> dto);
}
