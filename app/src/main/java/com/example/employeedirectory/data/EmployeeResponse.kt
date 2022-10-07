package com.example.employeedirectory.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EmployeeResponse(
    val employees: List<Employee>
)