package com.example.employeedirectory.di

import com.example.employeedirectory.BuildConfig
import com.example.employeedirectory.EmployeeApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class NetworkDependencyInjectorImpl : NetworkDependencyInjector {
    override fun provideAPI(): EmployeeApi {
        val okHttpClient = OkHttpClient.Builder()
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
        return retrofit.create()
    }
}