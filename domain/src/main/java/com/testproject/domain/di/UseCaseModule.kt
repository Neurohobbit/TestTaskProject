package com.testproject.domain.di

import com.testproject.domain.usecases.GetTodoUseCase
import com.testproject.domain.usecases.GetTodosUseCase
import com.testproject.domain.usecases.UpdateTodosUseCase
import org.koin.core.component.KoinApiExtension
import org.koin.dsl.module

@OptIn(KoinApiExtension::class)
val useCasesModule = module {
    single { GetTodosUseCase(get()) }
    single { GetTodoUseCase(get()) }
    single { UpdateTodosUseCase(get()) }
}
