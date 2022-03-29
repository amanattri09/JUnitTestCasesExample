package com.app.baseprojectamanattri.di

import com.app.baseprojectamanattri.injection.ApplicationModule
import com.app.baseprojectamanattri.network.api.ApiService

class TestNetworkDependencyProvider {

    fun getApiService(): ApiService {
        return ApplicationModule().run {
            providesApiService(providesRetrofit(providesOkHttp(),providesUrl()))
        }
    }
}