package com.example.employeedirectory.data.contract

import com.example.employeedirectory.data.Employee

interface EmployeeRemoteStore {
    suspend fun fetchEmployees(): List<Employee>

    //region Testing
    /**
     * DO NOT USE -- only used for testing purposes
     * DISCLAIMER -- I would not leave code like this in a real app and in prod. This is just for ease of testing
     */
    suspend fun fetchMalformedEmployees(): List<Employee>

    /**
     * DO NOT USE -- only used for testing purposes
     * DISCLAIMER -- I would not leave code like this in a real app and in prod. This is just for ease of testing
     */
    suspend fun fetchEmptyEmployees(): List<Employee>
}