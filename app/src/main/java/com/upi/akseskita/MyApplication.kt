package com.upi.akseskita

import android.app.Application
import com.upi.akseskita.core.di.networkModule
import com.upi.akseskita.core.di.repositoryModule
import com.upi.akseskita.feature.di.useCaseModule
import com.upi.akseskita.feature.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule,
                )
            )
        }
    }
}