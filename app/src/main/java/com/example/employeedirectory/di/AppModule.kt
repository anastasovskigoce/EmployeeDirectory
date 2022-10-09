package com.example.employeedirectory.di

import com.example.employeedirectory.BuildConfig
import com.example.employeedirectory.EmployeeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideEmployee(): EmployeeApi {
        val okHttpClient = OkHttpClient.Builder()
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
        return retrofit.create(EmployeeApi::class.java)
    }
}
