package com.iloadti.testegithub

import android.app.Application
import com.iloadti.testegithub.di.*
import com.iloadti.testegithub.di.apiModuleService
import com.iloadti.testegithub.di.moduleGitHubRepository
import com.iloadti.testegithub.di.schedulerProviderModule
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppApplication)
            fragmentFactory()
            modules(fragmentModules, apiModuleService, moduleGitHubRepository, schedulerProviderModule)
        }
    }


}