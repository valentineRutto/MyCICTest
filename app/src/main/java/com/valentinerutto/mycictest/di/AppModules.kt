package com.valentinerutto.mycictest.di

import com.valentinerutto.mycictest.App
import com.valentinerutto.mycictest.data.LoginRepository
import com.valentinerutto.mycictest.data.netwokr.ApiService
import com.valentinerutto.mycictest.data.netwokr.RetrofitClient.createOkClient
import com.valentinerutto.mycictest.data.netwokr.RetrofitClient.createRetrofit
import com.valentinerutto.mycictest.ui.LoginViewmodel
import com.valentinerutto.mycictest.utils.Constants
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val appModules = module {

    single { App.INSTANCE }
    single<ApiService> { (get() as Retrofit).create(ApiService::class.java) }
    single { createOkClient() }

    single {
        createRetrofit(
            baseUrl = Constants.BASE_URL, get()
        )
    }


    single { LoginRepository(get()) }

    viewModel { LoginViewmodel(get()) }
}