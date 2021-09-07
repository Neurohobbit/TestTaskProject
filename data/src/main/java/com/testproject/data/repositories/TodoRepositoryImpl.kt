package com.testproject.data.repositories

import com.testproject.data.network.api.ApiService
import com.testproject.data.network.response.toTodoEntity
import com.testproject.data.persistence.dao.TodoDao
import com.testproject.data.persistence.dao.toTodoItem
import com.testproject.domain.core.result.asSuccess
import com.testproject.domain.core.result.isSuccess
import com.testproject.domain.entities.TodoItem
import com.testproject.domain.repositories.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

internal class TodoRepositoryImpl(
    private val apiService: ApiService,
    private val todoDao: TodoDao
) : TodoRepository {

    override suspend fun getTodo(id: Int): Flow<TodoItem> =
        withContext(Dispatchers.IO) {
            return@withContext todoDao.getTodo(id).map { it.toTodoItem() }
        }

    override suspend fun getTodos(): Flow<List<TodoItem>> =
        withContext(Dispatchers.IO) {
            val items = todoDao.getTodos()
            return@withContext items.map { list ->
                list.map { it.toTodoItem() } ?: emptyList()
            }
        }

    override suspend fun updateTodosList() {
        withContext(Dispatchers.IO) {
            val todosResponse = apiService.fetchTodos()
            if (todosResponse.isSuccess()) {
                val todoEntityList = todosResponse.asSuccess().value.todoItems.map { it.toTodoEntity() }
                todoDao.insertAll(
                    todoEntityList
                )
            }
        }
    }
}
