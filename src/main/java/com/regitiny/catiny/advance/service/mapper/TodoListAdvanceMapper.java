package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.TodoListModel;
import com.regitiny.catiny.domain.TodoList;
import com.regitiny.catiny.service.dto.TodoListDTO;
import org.mapstruct.Mapper;

/**
 * this is a custom mapper for each entity .
 * this mapper extend from Jhipster mapper
 * ( d=dto, e=entity , m=model and To=2 ) --> ( dtoToEntity = d2e , modelToDto = m2d ).
 */
@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface TodoListAdvanceMapper extends EntityAdvanceMapper<TodoListModel, TodoListDTO, TodoList>
{
}
