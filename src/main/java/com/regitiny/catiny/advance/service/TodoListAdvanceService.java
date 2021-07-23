package com.regitiny.catiny.advance.service;

import com.regitiny.catiny.advance.repository.TodoListAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.TodoListAdvanceSearch;
import com.regitiny.catiny.advance.service.mapper.TodoListAdvanceMapper;
import com.regitiny.catiny.domain.TodoList;
import com.regitiny.catiny.service.TodoListQueryService;
import com.regitiny.catiny.service.TodoListService;

/**
 * AdvanceService layer for {@link TodoList}.
 *
 * @see TodoListService is base service generate by jhipster
 */
public interface TodoListAdvanceService extends BaseSrvice<TodoList, TodoListService, TodoListQueryService, TodoListAdvanceMapper, TodoListAdvanceRepository, TodoListAdvanceSearch>
{
}
