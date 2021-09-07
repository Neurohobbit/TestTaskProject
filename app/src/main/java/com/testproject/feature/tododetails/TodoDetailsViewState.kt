package com.testproject.feature.tododetails

import com.testproject.base.Action
import com.testproject.base.ViewState
import com.testproject.domain.entities.TodoItem

data class TodoDetailsViewState(
    val todoItem: TodoItem? = null
) : ViewState

sealed class TodoDetailsAction : Action
