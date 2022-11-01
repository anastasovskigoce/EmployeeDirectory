package com.example.employeedirectory.data

import com.example.employeedirectory.EmployeeApi
import com.example.employeedirectory.data.contract.EmployeeRemoteStore
import javax.inject.Inject

class EmployeeRepository @Inject constructor(
    private val employeeApi: EmployeeApi,
    private val employeeDao: EmployeeDao
) : EmployeeRemoteStore {
    override suspend fun fetchEmployees(): List<Employee> {
        // Task 2 clear db when schema changes


        // Task 1 if the db is empty pull from network, else use DB
        val localEmployeeList = employeeDao.getEmployee()
        if (localEmployeeList.isEmpty()){
            // get from remote source
            val employees = employeeApi.fetchEmployees().employees
            employeeDao.insertAllEmployees(employees.map { EmployeeEnt.convertFromNetwork(it) })
            return employees
        }
        else{
            return localEmployeeList.map { Employee.convertFromDB(it) }
        }
    }

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