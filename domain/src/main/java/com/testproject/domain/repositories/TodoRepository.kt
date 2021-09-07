package com.testproject.domain.repositories

import com.testproject.domain.entities.TodoItem
import kotlinx.coroutines.flow.Flow

interface TodoRepository {

    suspend fun getTodo(id: Int): Flow<TodoItem>
    suspend fun getTodos(): Flow<List<TodoItem>>

    suspend fun updateTodosList()
}
