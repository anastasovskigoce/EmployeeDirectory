package com.example.employeedirectory.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.employeedirectory.MainCoroutineRule
import com.example.employeedirectory.data.Employee
import com.example.employeedirectory.data.EmployeeEnum
import com.example.employeedirectory.data.contract.EmployeeRemoteStore
import com.example.employeedirectory.presentation.EmployeeDirectoryViewState.*
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class EmployeeDirectoryListViewModelTests {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var subjectUnderTest: EmployeeDirectoryListViewModel

    private val repo = mock(EmployeeRemoteStore::class.java)

    @Before
    fun setup() {
        subjectUnderTest = EmployeeDirectoryListViewModel(repo)
    }

    @Test
    fun `Empty state works`() = runBlocking {
        whenever(repo.fetchEmployees()).thenReturn(emptyList())
        subjectUnderTest.fetchEmployees()
        assertThat(EmptyListOfEmployeesFetched).isEqualTo(
            subjectUnderTest.uiState.value
        )
    }

    @Test
    fun `Happy path work`() = runBlocking {
        val employees = listOf(
            Employee(
                id = "1",
                fullName = "John Doe",
                phoneNumber = "2",
                email = "jd@mail.com",
                biography = "bio",
                photoUrlSmall = "smallURL",
                photoUrlLarge = "largeURK",
                team = "core",
                employeeType = EmployeeEnum.CONTRACTOR
            )
        )
        whenever(repo.fetchEmployees()).thenReturn(employees)
        subjectUnderTest.fetchEmployees()
        assertThat(EmployeesFetched(employees)).isEqualTo(subjectUnderTest.uiState.value)
    }

    @Test
    fun `Failure state works`() = runBlocking {
        whenever(repo.fetchEmployees()).thenThrow(RuntimeException("Error"))
        subjectUnderTest.fetchEmployees()
        assertThat(Error).isEqualTo(subjectUnderTest.uiState.value)
    }
}