package com.app.baseprojectamanattri.injection

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.app.baseprojectamanattri.BuildConfig
import com.app.baseprojectamanattri.network.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    fun providesUrl():String="https://jsonplaceholder.typicode.com"

    @Provides
    @Singleton
    fun providesOkHttp():OkHttpClient=if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else OkHttpClient
        .Builder()
        .build()

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient,baseUrl:String):Retrofit= Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(
            GsonConverterFactory
            .create())
        .build()

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): ApiService=retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideSharedPref(application: Application):SharedPreferences{
        return PreferenceManager.getDefaultSharedPreferences(application)
    }

}