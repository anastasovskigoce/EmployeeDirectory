package com.example.employeedirectory.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.employeedirectory.data.Employee
import com.example.employeedirectory.data.EmployeeRepository
import com.example.employeedirectory.presentation.EmployeeDirectoryViewState.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EmployeeDirectoryListViewModel : ViewModel() {
    private val employeeRepository = EmployeeRepository()

    private val _uiState: MutableStateFlow<EmployeeDirectoryViewState> =
        MutableStateFlow(Loading)
    val uiState: StateFlow<EmployeeDirectoryViewState>
        get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch { fetchEmployees() }
    }

    suspend fun fetchEmployees() {
        // show that we are getting the employees
        _uiState.value = Loading

        // load them
        val items = employeeRepository.fetchEmployees().sortedBy { it.fullName }
        _uiState.value = EmployeesFetched(items)
    }
}

sealed class EmployeeDirectoryViewState {
    /**
     * The loading state, waiting on a response
     */
    object Loading : EmployeeDirectoryViewState()

    /**
     * We received the list of employees, we can show them on the screen
     */
    data class EmployeesFetched(val employees: List<Employee>) : EmployeeDirectoryViewState()

    /**
     * An error occurred getting the employees from the API, show the error screen
     */
    object Error : EmployeeDirectoryViewState()

    /**
     * No employees fetched
     */
    object NoEmployeesFetched : EmployeeDirectoryViewState()
}