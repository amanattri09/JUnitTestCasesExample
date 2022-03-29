package com.app.baseprojectamanattri.network

import android.app.Application
import com.app.baseprojectamanattri.presentation.common.isNetworkAvailable
import javax.inject.Inject

class ConnectionHelper @Inject constructor(val context: Application) {

    fun isConnected(): Boolean {
        return true
    }

}