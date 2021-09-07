package com.testproject.feature.tododetails

import com.testproject.base.BaseViewModel
import com.testproject.domain.usecases.GetTodoUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TodoDetailsViewModel(
    private val todoItemId: Int,
    private val getTodoUseCase: GetTodoUseCase
) : BaseViewModel<TodoDetailsViewState, TodoDetailsAction>() {

    override fun initialViewState() = TodoDetailsViewState()

    init {
        launch {
            getTodoUseCase(todoItemId)
                .collect { item ->
                    reduceViewState {
                        it.copy(
                            todoItem = item
                        )
                    }
                }
        }
    }
}
