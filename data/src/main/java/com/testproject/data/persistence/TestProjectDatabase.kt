package com.testproject.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.testproject.data.persistence.converters.TodoListConverter
import com.testproject.data.persistence.dao.TodoDao
import com.testproject.data.persistence.dao.TodoEntity

@Database(
    entities = [
        TodoEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    value = [
        TodoListConverter::class
    ]
)
internal abstract class TestProjectDatabase : RoomDatabase() {

    abstract val todoDao: TodoDao
}
