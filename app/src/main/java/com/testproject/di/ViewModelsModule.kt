package com.testproject.di

import com.testproject.feature.tododetails.TodoDetailsViewModel
import com.testproject.feature.todoslist.TodosListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { TodosListViewModel(get(), get()) }
    viewModel { (todoItemId: Int) -> TodoDetailsViewModel(todoItemId, get()) }
}
