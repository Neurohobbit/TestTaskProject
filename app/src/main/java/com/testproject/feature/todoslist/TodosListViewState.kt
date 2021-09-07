package com.testproject.feature.todoslist

import com.testproject.base.Action
import com.testproject.base.ViewState
import com.testproject.domain.entities.TodoItem

data class TodosListViewState(
    val todosList: List<TodoItem> = emptyList()
) : ViewState

sealed class TodoListAction : Action {
    data class ShowTodoDetailsScreen(val id: Int) : TodoListAction()
}
