package com.example.employeedirectory.data

import com.example.employeedirectory.EmployeeApi
import com.example.employeedirectory.data.contract.EmployeeRemoteStore
import javax.inject.Inject

class EmployeeRepository @Inject constructor(
    private val employeeApi: EmployeeApi
) : EmployeeRemoteStore {
    override suspend fun fetchEmployees(): List<Employee> = employeeApi.fetchEmployees().employees

    //region Testing
    /**
     * DO NOT USE -- only used for testing purposes
     * DISCLAIMER -- I would not leave code like this in a real app and in prod. This is just for ease of testing
     */
    override suspend fun fetchMalformedEmployees(): List<Employee> =
        employeeApi.fetchMalformedEmployees().employees

    /**
     * DO NOT USE -- only used for testing purposes
     * DISCLAIMER -- I would not leave code like this in a real app and in prod. This is just for ease of testing
     */
    override suspend fun fetchEmptyEmployees(): List<Employee> =
        employeeApi.fetchEmptyEmployees().employees
    //endregion
}