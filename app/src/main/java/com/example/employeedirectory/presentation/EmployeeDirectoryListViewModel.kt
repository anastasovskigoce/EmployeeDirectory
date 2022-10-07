package com.example.employeedirectory.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.employeedirectory.data.Employee
import com.example.employeedirectory.data.EmployeeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EmployeeDirectoryListViewModel : ViewModel() {
    private val employeeRepository = EmployeeRepository()

    private val _uiState: MutableStateFlow<EmployeeDirectoryUIState> = MutableStateFlow(EmployeeDirectoryUIState())
    val uiState: StateFlow<EmployeeDirectoryUIState>
        get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val items = employeeRepository.fetchEmployees().sortedBy { it.fullName }
            _uiState.update { oldState ->
                oldState.copy(
                    employees = items
                )
            }
        }
    }
}

data class EmployeeDirectoryUIState(
    val employees: List<Employee> = listOf()
)