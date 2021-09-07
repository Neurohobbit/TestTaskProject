package com.testproject

import android.app.Application
import com.testproject.data.di.repositoriesModule
import com.testproject.data.di.retrofitModule
import com.testproject.di.viewModelsModule
import com.testproject.domain.di.useCasesModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class TestProjectApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@TestProjectApp)
            modules(
                listOf(
                    repositoriesModule,
                    viewModelsModule,
                    useCasesModule,
                    retrofitModule
                )
            )
        }
    }
}
