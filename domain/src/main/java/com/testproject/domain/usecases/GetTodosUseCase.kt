package com.testproject.domain.usecases

import com.testproject.domain.base.BaseUseCase
import com.testproject.domain.entities.TodoItem
import com.testproject.domain.repositories.TodoRepository
import kotlinx.coroutines.flow.Flow

class GetTodosUseCase(
    private val todoRepository: TodoRepository
) : BaseUseCase<Unit, Flow<List<TodoItem>>> {

    override suspend fun invoke(params: Unit?): Flow<List<TodoItem>> =
        todoRepository.getTodos()
}
