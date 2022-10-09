package com.example.employeedirectory.di

import com.example.employeedirectory.data.EmployeeRepository
import com.example.employeedirectory.data.contract.EmployeeRemoteStore
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class EmployeeRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMyRepository(
        employeeRepository: EmployeeRepository
    ): EmployeeRemoteStore
}