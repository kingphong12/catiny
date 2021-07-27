package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.FriendModel;
import com.regitiny.catiny.domain.Friend;
import com.regitiny.catiny.service.dto.FriendDTO;
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
public interface FriendAdvanceMapper extends EntityAdvanceMapper<FriendModel, FriendDTO, Friend>
{
  FriendDTO request2d(FriendModel.Request request);


  List<FriendDTO> request2d(List<FriendModel.Request> request);


  FriendModel.Response d2Response(FriendDTO dto);


  List<FriendModel.Response> d2Response(List<FriendDTO> dto);
}
