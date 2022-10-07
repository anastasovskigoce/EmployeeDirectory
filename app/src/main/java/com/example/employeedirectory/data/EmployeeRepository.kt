package com.example.employeedirectory.data

import com.example.employeedirectory.EmployeeApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

private const val BASE_URL = "https://s3.amazonaws.com/sq-mobile-interview/"

class EmployeeRepository {
    private val employeeApi: EmployeeApi

    init {
        val okHttpClient = OkHttpClient.Builder()
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
        employeeApi = retrofit.create()
    }

    suspend fun fetchEmployees(): List<Employee> = employeeApi.fetchEmployees().employees

    //ONly used for testing
    suspend fun fetchMalformedEmployees(): List<Employee> = employeeApi.fetchMalformedEmployees().employees

    //Only used for testing
    suspend fun fetchEmptyEmployees(): List<Employee> = employeeApi.fetchEmptyEmployees().employees
}