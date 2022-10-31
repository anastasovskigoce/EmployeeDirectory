package com.example.employeedirectory.data

import com.example.employeedirectory.EmployeeApi
import com.example.employeedirectory.data.contract.EmployeeRemoteStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class EmployeeRepository @Inject constructor(
    private val employeeApi: EmployeeApi,
    private val employeeDao: EmployeeDao
//    private val coroutineScope: CoroutineScope = GlobalScope
) : EmployeeRemoteStore {

    // this would fetch either from DB or network
    override suspend fun fetchEmployees(): List<Employee> {
        // get the employees over the network
        val employeesFetched = employeeApi.fetchEmployees().employees
        // cache the response in the DB
        employeeDao.insertAll(employeesFetched.map { EmployeeEntity.convert(it) })
        return employeesFetched
    }

    suspend fun getEmployee(id: String): EmployeeEntity = employeeDao.getEmployee(id)

//    fun updateCrime(empl: EmployeeEntity) {
//        coroutineScope.launch {
//            employeeDao.updateEmployee(empl)
//        }
//    }

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