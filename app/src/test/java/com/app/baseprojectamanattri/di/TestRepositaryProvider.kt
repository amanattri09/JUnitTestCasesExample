package com.app.baseprojectamanattri.di

import android.app.Application
import com.app.baseprojectamanattri.data.local.SharedPrefManager
import com.app.baseprojectamanattri.data.remote.post.repo.PostRepositaryImp
import com.app.baseprojectamanattri.di.TestNetworkDependencyProvider
import com.app.baseprojectamanattri.domain.post.repositary.PostRepositary
import com.app.baseprojectamanattri.network.ConnectionHelper
import com.app.baseprojectamanattri.network.ConnectionHelper_Factory

class TestRepositaryProvider {

    fun getPostRepositary(application: Application,sharedPrefManager: SharedPrefManager): PostRepositary{
        val connectionHelper = ConnectionHelper(application)
        return PostRepositaryImp(TestNetworkDependencyProvider().getApiService(),sharedPrefManager,connectionHelper)
    }

}