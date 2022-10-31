package com.example.employeedirectory.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [EmployeeEntity::class], version = 1)
abstract class EmployeeDatabase : RoomDatabase(){

    abstract fun getEmployeeDao() : EmployeeDao
}