package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.TodoListAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.TodoListAdvanceSearch;
import com.regitiny.catiny.advance.service.TodoListAdvanceService;
import com.regitiny.catiny.advance.service.mapper.TodoListAdvanceMapper;
import com.regitiny.catiny.domain.TodoList;
import com.regitiny.catiny.service.TodoListQueryService;
import com.regitiny.catiny.service.TodoListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class TodoListAdvanceServiceImpl extends AdvanceService<TodoList, TodoListService, TodoListQueryService, TodoListAdvanceMapper, TodoListAdvanceRepository, TodoListAdvanceSearch> implements TodoListAdvanceService
{
  private final TodoListAdvanceRepository todoListAdvanceRepository;

  private final TodoListAdvanceSearch todoListAdvanceSearch;

  private final TodoListAdvanceMapper todoListAdvanceMapper;
}
