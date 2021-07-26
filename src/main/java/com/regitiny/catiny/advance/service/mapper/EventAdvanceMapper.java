package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.EventModel;
import com.regitiny.catiny.domain.Event;
import com.regitiny.catiny.service.dto.EventDTO;
import com.regitiny.catiny.service.mapper.EventMapper;
import com.regitiny.catiny.service.mapper.EventMapperImpl;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface EventAdvanceMapper extends EntityAdvanceMapper<EventModel, EventDTO, Event>
{
  EventMapper baseMapper = new EventMapperImpl();

  EventAdvanceMapper thisMapper = new EventAdvanceMapperImpl();


  EventDTO request2d(EventModel.Request request);


  List<EventDTO> request2d(List<EventModel.Request> request);


  EventModel.Response d2Response(EventDTO dto);


  List<EventModel.Response> d2Response(List<EventDTO> dto);


  @Override
  default EventModel e2m(Event entity)
  {
    return thisMapper.d2m(baseMapper.toDto(entity));
  }


  @Override
  default List<EventModel> e2m(List<Event> entityList)
  {
    return thisMapper.d2m(baseMapper.toDto(entityList));
  }


  @Override
  default Event d2e(EventDTO dto)
  {
    return baseMapper.toEntity(dto);
  }


  @Override
  default List<Event> d2e(List<EventDTO> dtoList)
  {
    return baseMapper.toEntity(dtoList);
  }


  @Override
  default EventDTO e2d(Event entity)
  {
    return baseMapper.toDto(entity);
  }


  @Override
  default List<EventDTO> e2d(List<Event> entityList)
  {
    return baseMapper.toDto(entityList);
  }
}
