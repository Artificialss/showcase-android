package com.artificialss.showcase.android

import android.app.Application
import com.artificialss.showcase.android.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ShowcaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ShowcaseApplication)
            modules(appModule)
        }
    }
}
