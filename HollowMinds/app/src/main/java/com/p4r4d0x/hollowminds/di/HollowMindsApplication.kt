package com.p4r4d0x.hollowminds.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class HollowMindsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@HollowMindsApplication)
            modules(viewmodelModule, usecasesModules, repositoriesModule, datasourcesModule)
        }
    }
}