package com.app.baseprojectamanattri.di

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.app.baseprojectamanattri.data.local.SharedPrefManager
import dagger.Provides
import javax.inject.Singleton

class TestApplicationDependencyProvider {

    fun provideSharedPref(application: Application): SharedPrefManager {
        return SharedPrefManager(PreferenceManager.getDefaultSharedPreferences(application))
    }
}