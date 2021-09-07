package com.testproject.feature.todoslist

import com.testproject.base.BaseViewModel
import com.testproject.domain.usecases.GetTodosUseCase
import com.testproject.domain.usecases.UpdateTodosUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TodosListViewModel(
    private val getTodosUseCase: GetTodosUseCase,
    private val updateTodosUseCase: UpdateTodosUseCase
) : BaseViewModel<TodosListViewState, TodoListAction>() {

    override fun initialViewState() = TodosListViewState()

    init {
        launch {
            getTodosUseCase()
                .collect { items ->
                    reduceViewState {
                        it.copy(
                            todosList = items
                        )
                    }
                }
        }

        launch {
            updateTodosUseCase()
        }
    }

    fun onItemClicked(id: Int) {
        sendAction(TodoListAction.ShowTodoDetailsScreen(id))
    }
}
