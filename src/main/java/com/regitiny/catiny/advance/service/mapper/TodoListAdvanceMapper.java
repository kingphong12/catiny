package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.TodoListModel;
import com.regitiny.catiny.domain.TodoList;
import com.regitiny.catiny.service.dto.TodoListDTO;
import com.regitiny.catiny.service.mapper.TodoListMapper;
import com.regitiny.catiny.service.mapper.TodoListMapperImpl;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface TodoListAdvanceMapper extends EntityAdvanceMapper<TodoListModel, TodoListDTO, TodoList>
{
  TodoListMapper baseMapper = new TodoListMapperImpl();

  TodoListAdvanceMapper thisMapper = new TodoListAdvanceMapperImpl();


  TodoListDTO request2d(TodoListModel.Request request);


  List<TodoListDTO> request2d(List<TodoListModel.Request> request);


  TodoListModel.Response d2Response(TodoListDTO dto);


  List<TodoListModel.Response> d2Response(List<TodoListDTO> dto);


  @Override
  default TodoListModel e2m(TodoList entity)
  {
    return thisMapper.d2m(baseMapper.toDto(entity));
  }


  @Override
  default List<TodoListModel> e2m(List<TodoList> entityList)
  {
    return thisMapper.d2m(baseMapper.toDto(entityList));
  }


  @Override
  default TodoList d2e(TodoListDTO dto)
  {
    return baseMapper.toEntity(dto);
  }


  @Override
  default List<TodoList> d2e(List<TodoListDTO> dtoList)
  {
    return baseMapper.toEntity(dtoList);
  }


  @Override
  default TodoListDTO e2d(TodoList entity)
  {
    return baseMapper.toDto(entity);
  }


  @Override
  default List<TodoListDTO> e2d(List<TodoList> entityList)
  {
    return baseMapper.toDto(entityList);
  }
}
