package com.testproject.domain.usecases

import com.testproject.domain.base.BaseUseCase
import com.testproject.domain.entities.TodoItem
import com.testproject.domain.repositories.TodoRepository
import kotlinx.coroutines.flow.Flow

class GetTodoUseCase(
    private val todoRepository: TodoRepository
) : BaseUseCase<Int, Flow<TodoItem>> {

    override suspend fun invoke(params: Int?): Flow<TodoItem> =
        todoRepository.getTodo(id = params!!)
}
