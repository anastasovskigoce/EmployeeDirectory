package com.example.employeedirectory.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface EmployeeDao {

    @Query("SELECT * FROM employeeent")
    suspend fun getEmployee() : List<EmployeeEnt>

    @Insert
    suspend fun insertAllEmployees(employees: List<EmployeeEnt>)
}