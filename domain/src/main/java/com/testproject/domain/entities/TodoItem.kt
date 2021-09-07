package com.testproject.domain.entities

data class TodoItem(
    val id: Int,
    val title: String,
    val dueOn: String,
    val status: String
)
