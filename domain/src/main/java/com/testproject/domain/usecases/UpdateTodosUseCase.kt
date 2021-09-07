package com.testproject.domain.usecases

import com.testproject.domain.base.BaseUseCase
import com.testproject.domain.repositories.TodoRepository

class UpdateTodosUseCase(
    private val todoRepository: TodoRepository
) : BaseUseCase<Unit, Unit> {

    override suspend fun invoke(params: Unit?): Unit =
        todoRepository.updateTodosList()
}
