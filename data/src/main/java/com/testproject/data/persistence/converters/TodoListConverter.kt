package com.testproject.data.persistence.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.testproject.data.fromJson
import com.testproject.data.persistence.dao.TodoEntity

class TodoListConverter {
    private val gson = Gson()

    @TypeConverter
    fun toColumn(list: List<TodoEntity>): String = gson.toJson(list)

    @TypeConverter
    fun toList(column: String): List<TodoEntity> = gson.fromJson(column)!!
}
