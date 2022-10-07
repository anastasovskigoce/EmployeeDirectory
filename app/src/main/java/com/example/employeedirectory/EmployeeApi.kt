package com.example.employeedirectory

import com.example.employeedirectory.data.EmployeeResponse
import retrofit2.http.GET

interface EmployeeApi {
    @GET("employees.json")
    suspend fun fetchEmployees(): EmployeeResponse

    @GET("employees_malformed.json")
    suspend fun fetchMalformedEmployees(): EmployeeResponse

    @GET("employees_empty.json")
    suspend fun fetchEmptyEmployees(): EmployeeResponse
}