package com.app.baseprojectamanattri.di

import com.app.baseprojectamanattri.injection.ApplicationModule
import com.app.baseprojectamanattri.network.api.ApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit

class TestNetworkDependencyProvider {

    fun getApiService(): ApiService {
        return ApplicationModule().run {
            providesApiService(providesRetrofit(providesOkHttp(),providesUrl()))
        }
    }
}