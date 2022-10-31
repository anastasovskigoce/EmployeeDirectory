package com.example.employeedirectory.data

import androidx.room.*

@Dao
interface EmployeeDao {

    @Query("SELECT * FROM employeeentity WHERE id=(:id)")
    suspend fun getEmployee(id: String) : EmployeeEntity

    @Update
    suspend fun updateEmployee(employeeEntity: EmployeeEntity)

    @Insert
    fun insertAll(employees: List<EmployeeEntity>)

    @Insert
    fun insert(employee: EmployeeEntity)

    @Delete
    fun delete(employee: EmployeeEntity)
}