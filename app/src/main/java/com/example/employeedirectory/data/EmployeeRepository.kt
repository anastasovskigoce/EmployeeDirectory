package com.example.employeedirectory.data

import com.example.employeedirectory.BuildConfig
import com.example.employeedirectory.EmployeeApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class EmployeeRepository {
    private val employeeApi: EmployeeApi

    init {
        val okHttpClient = OkHttpClient.Builder()
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
        employeeApi = retrofit.create()
    }

    suspend fun fetchEmployees(): List<Employee> = employeeApi.fetchEmployees().employees

    //region Testing
    /**
     * DO NOT USE -- only used for testing purposes
     * DISCLAIMER -- I would not leave code like this in a real app and in prod. This is just for ease of testing
     */
    suspend fun fetchMalformedEmployees(): List<Employee> = employeeApi.fetchMalformedEmployees().employees

     /**
     * DO NOT USE -- only used for testing purposes
     * DISCLAIMER -- I would not leave code like this in a real app and in prod. This is just for ease of testing
     */
    suspend fun fetchEmptyEmployees(): List<Employee> = employeeApi.fetchEmptyEmployees().employees
    //endregion
}