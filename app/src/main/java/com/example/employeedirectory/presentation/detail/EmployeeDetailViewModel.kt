package com.example.employeedirectory.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.employeedirectory.data.EmployeeEntity
import com.example.employeedirectory.data.EmployeeRepository
import com.example.employeedirectory.presentation.detail.EmployeeDetailViewModel.EmployeeDetailViewState.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class EmployeeDetailViewModel(id: String) : ViewModel() {

    @Inject
    lateinit var repo: EmployeeRepository

    private val _uiState: MutableStateFlow<EmployeeDetailViewState> =
        MutableStateFlow(Loading)
    val uiState: StateFlow<EmployeeDetailViewState>
        get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.value = EmployeesFetched(repo.getEmployee(id))
        }
    }


    class EmployeeDetailViewModelFactory(
        private val id: String
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return EmployeeDetailViewModel(id) as T
        }
    }


    sealed class EmployeeDetailViewState {
        /**
         * The loading state, waiting on a response
         */
        object Loading : EmployeeDetailViewState()

        /**
         * We received an employee
         */
        data class EmployeesFetched(val employee: EmployeeEntity) : EmployeeDetailViewState()
    }
}