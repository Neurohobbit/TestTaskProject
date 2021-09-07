package com.testproject.data.persistence.dao

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.testproject.domain.entities.TodoItem

@Entity(tableName = "todo")
data class TodoEntity(
    @PrimaryKey
    val id: Int,
    val userId: Int,
    val title: String,
    val status: String,
    val dueOn: String
)

internal fun TodoEntity.toTodoItem(): TodoItem {
    return TodoItem(
        id = id,
        title = title,
        status = status,
        dueOn = dueOn
    )
}
