package com.testproject.data.di

import android.content.Context
import androidx.room.Room
import com.testproject.data.network.api.ApiService
import com.testproject.data.persistence.TestProjectDatabase
import com.testproject.data.repositories.TodoRepositoryImpl
import com.testproject.domain.repositories.TodoRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.dsl.module
import retrofit2.Retrofit
import timber.log.Timber

val repositoriesModule = module {

    fun provideDatabase(context: Context): TestProjectDatabase {
        return Room.databaseBuilder(context, TestProjectDatabase::class.java, "testproject")
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<TestProjectDatabase>().todoDao }

    single {
        val errorHandler = CoroutineExceptionHandler { _, throwable ->
            Timber.e(throwable)
        }
        CoroutineScope(SupervisorJob() + errorHandler)
    }
    single { provideDatabase(get()) }
    single<TodoRepository> { TodoRepositoryImpl(get(), get()) }
    single { get<Retrofit>().create(ApiService::class.java) }
}
