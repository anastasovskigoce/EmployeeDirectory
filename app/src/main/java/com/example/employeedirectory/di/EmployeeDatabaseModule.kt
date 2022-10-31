package com.example.employeedirectory.di

import android.content.Context
import androidx.room.Room
import com.example.employeedirectory.data.EmployeeDao
import com.example.employeedirectory.data.EmployeeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DATABASE_NAME = "employee-database"

@InstallIn(SingletonComponent::class)
@Module
class EmployeeDatabaseModule {
    @Provides
    fun provideEmployeeDao(appDatabase: EmployeeDatabase): EmployeeDao {
        return appDatabase.getEmployeeDao()
    }


    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): EmployeeDatabase {
        return Room.databaseBuilder(
            appContext,
            EmployeeDatabase::class.java,
            DATABASE_NAME,
        ).build()
    }
}