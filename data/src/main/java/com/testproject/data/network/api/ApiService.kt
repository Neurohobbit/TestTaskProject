package com.testproject.data.network.api

import com.testproject.data.network.response.TodoResponse
import com.testproject.domain.core.result.Result
import retrofit2.http.GET

interface ApiService {

    @GET(".")
    suspend fun fetchTodos(): Result<TodoResponse>
}
