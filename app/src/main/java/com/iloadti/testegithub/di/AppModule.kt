package com.iloadti.testegithub.di

import android.app.Application
import com.iloadti.testegithub.BuildConfig
import com.iloadti.testegithub.data.ApiService
import com.iloadti.testegithub.data.service.Api
import com.iloadti.testegithub.network.retrofitConfig
import com.iloadti.testegithub.data.core.RequestApi
import com.iloadti.testegithub.data.core.RequestApiImpl
import com.iloadti.testegithub.data.repositories.SignRepositoryImpl
import com.iloadti.testegithub.domain.repositories.SignRepository
import com.iloadti.testegithub.domain.usecase.DetailItemUseCase
import com.iloadti.testegithub.domain.usecase.HomeUseCase
import com.iloadti.testegithub.domain.usecase.SignUseCase
import com.iloadti.testegithub.presentation.itemDetail.viewModel.DetailItemViewModel
import com.iloadti.testegithub.presentation.main.viewmodel.SignViewModel
import com.iloadti.testegithub.presentation.purchaseList.viewModel.HomeViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

class AppModule{

    val retrofit by lazy { retrofitConfig(BuildConfig.BSE_URL) }

    private val moduleApp = module {
        factory <Api>{ ApiService(retrofit) }
        factory <RequestApi>{ RequestApiImpl() }
        factory <SignRepository> { SignRepositoryImpl(Dispatchers.IO, get(), get()) }
        factory { SignUseCase(get()) }
        viewModel { SignViewModel(get()) }
        factory { HomeUseCase(get()) }
        viewModel { HomeViewModel(get()) }
        factory { DetailItemUseCase(get()) }
        viewModel { DetailItemViewModel(get()) }
    }

    fun init(application: Application) {
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(application)
            modules(arrayListOf(moduleApp))
        }
    }
}