package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.MessageContentModel;
import com.regitiny.catiny.domain.MessageContent;
import com.regitiny.catiny.service.dto.MessageContentDTO;
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
public interface MessageContentAdvanceMapper extends EntityAdvanceMapper<MessageContentModel, MessageContentDTO, MessageContent>
{
  MessageContentDTO request2d(MessageContentModel.Request request);


  List<MessageContentDTO> request2d(List<MessageContentModel.Request> request);


  MessageContentModel.Response d2Response(MessageContentDTO dto);


  List<MessageContentModel.Response> d2Response(List<MessageContentDTO> dto);
}
