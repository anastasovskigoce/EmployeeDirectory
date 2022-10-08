package com.example.employeedirectory.presentation

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.employeedirectory.databinding.EmployeeDirectoryListFragmentBinding
import androidx.lifecycle.repeatOnLifecycle
import com.example.employeedirectory.R
import com.example.employeedirectory.presentation.EmployeeDirectoryViewState.*
import kotlinx.coroutines.launch

class EmployeeDirectoryListFragment : Fragment() {

    private var _binding: EmployeeDirectoryListFragmentBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    private val viewModel: EmployeeDirectoryListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = EmployeeDirectoryListFragmentBinding.inflate(inflater, container, false)

        binding.employeeDirRecyclerView.layoutManager = LinearLayoutManager(context)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { handleViewStateUpdate(it) }
            }
        }
    }

    //region ViewState handlers
    private fun handleViewStateUpdate(state: EmployeeDirectoryViewState) {
        when (state) {
            is Loading -> showLoading()
            is EmployeesFetched -> showEmployees(state)
            is Error -> showError()
            is EmptyListOfEmployeesFetched -> showNoEmployeesFound()
        }
    }

    private fun showEmployees(state: EmployeesFetched) {
        binding.shimmerLayout.stopShimmer()
        binding.shimmerLayout.visibility = View.GONE

        binding.noEmployeesFetchedGroup.visibility = View.GONE

        binding.employeeDirRecyclerView.visibility = View.VISIBLE
        binding.employeeDirRecyclerView.adapter = EmployeeDirectoryListAdapter(state.employees)
    }

    private fun showLoading() {
        binding.employeeDirRecyclerView.visibility = View.GONE

        binding.noEmployeesFetchedGroup.visibility = View.GONE

        binding.shimmerLayout.visibility = View.VISIBLE
        binding.shimmerLayout.startShimmer()
    }

    private fun showError() {}

    private fun showNoEmployeesFound() {
        binding.employeeDirRecyclerView.visibility = View.GONE

        binding.shimmerLayout.visibility = View.GONE

        binding.noEmployeesFetchedGroup.visibility = View.VISIBLE
    }

    //endregion

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.employee_directory_list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_item_refresh -> {
                viewLifecycleOwner.lifecycleScope.launch { viewModel.fetchEmployees() }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}