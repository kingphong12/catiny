package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.NotificationModel;
import com.regitiny.catiny.domain.Notification;
import com.regitiny.catiny.service.dto.NotificationDTO;
import com.regitiny.catiny.service.mapper.NotificationMapper;
import com.regitiny.catiny.service.mapper.NotificationMapperImpl;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface NotificationAdvanceMapper extends EntityAdvanceMapper<NotificationModel, NotificationDTO, Notification>
{
  NotificationMapper baseMapper = new NotificationMapperImpl();

  NotificationAdvanceMapper thisMapper = new NotificationAdvanceMapperImpl();


  NotificationDTO request2d(NotificationModel.Request request);


  List<NotificationDTO> request2d(List<NotificationModel.Request> request);


  NotificationModel.Response d2Response(NotificationDTO dto);


  List<NotificationModel.Response> d2Response(List<NotificationDTO> dto);


  @Override
  default NotificationModel e2m(Notification entity)
  {
    return thisMapper.d2m(baseMapper.toDto(entity));
  }


  @Override
  default List<NotificationModel> e2m(List<Notification> entityList)
  {
    return thisMapper.d2m(baseMapper.toDto(entityList));
  }


  @Override
  default Notification d2e(NotificationDTO dto)
  {
    return baseMapper.toEntity(dto);
  }


  @Override
  default List<Notification> d2e(List<NotificationDTO> dtoList)
  {
    return baseMapper.toEntity(dtoList);
  }


  @Override
  default NotificationDTO e2d(Notification entity)
  {
    return baseMapper.toDto(entity);
  }


  @Override
  default List<NotificationDTO> e2d(List<Notification> entityList)
  {
    return baseMapper.toDto(entityList);
  }
}
