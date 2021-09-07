package com.testproject.repository

import com.google.gson.Gson
import com.testproject.base.BaseUTTest
import com.testproject.data.network.response.TodoItemResponse
import com.testproject.data.network.response.TodoResponse
import com.testproject.data.persistence.dao.TodoDao
import com.testproject.di.configureTestAppComponent
import com.testproject.domain.usecases.UpdateTodosUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin

@RunWith(JUnit4::class)
class TodoRepositoryTest(
    private val updateTodosUseCase: UpdateTodosUseCase,
    private val todoDao: TodoDao
) : BaseUTTest() {

    @Before
    fun start() {
        super.setUp()

        startKoin { modules(configureTestAppComponent(getMockWebServerUrl())) }
    }

    @Test
    fun testParsingTodos() {
        val sampleResponse = getJson("success_resp_list.json")
        val todoItemsList: List<TodoItemResponse> = Gson().fromJson(sampleResponse, TodoResponse::class.java).todoItems

        assertEquals(todoItemsList.size, 20)
        assertEquals(todoItemsList[0].userId, 14)
        assertEquals(todoItemsList[14].title, "Unde statua bonus cribro angustus agnitio.")
    }

    @Test
    fun testSavingTodosToDatabase() =
        runBlocking {
            withContext(Dispatchers.IO) { updateTodosUseCase.invoke() }
            val items = todoDao.getTodos().first()
            assertEquals(items.size, 20)
        }
}
