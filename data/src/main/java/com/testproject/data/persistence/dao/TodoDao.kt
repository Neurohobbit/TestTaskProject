package com.testproject.data.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(todos: List<TodoEntity>): List<Long>

    @Query("SELECT * FROM todo")
    fun getTodos(): Flow<List<TodoEntity>>

    @Query("SELECT * FROM todo WHERE id = :id")
    fun getTodo(id: Int): Flow<TodoEntity>
}
