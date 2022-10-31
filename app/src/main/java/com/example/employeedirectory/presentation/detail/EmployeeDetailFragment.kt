package com.example.employeedirectory.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.employeedirectory.R
import com.example.employeedirectory.databinding.FragmentEmployeeDetailBinding
import com.example.employeedirectory.presentation.detail.EmployeeDetailViewModel.*
import com.example.employeedirectory.presentation.detail.EmployeeDetailViewModel.EmployeeDetailViewState.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

private const val TAG = "EmployeeDetailFragment"

@AndroidEntryPoint
class EmployeeDetailFragment : Fragment() {

    private val args: EmployeeDetailFragmentArgs by navArgs()
    private val viewModel: EmployeeDetailViewModel by viewModels {
        EmployeeDetailViewModelFactory(args.employeeID)
    }

    private var _binding: FragmentEmployeeDetailBinding? = null
    private val binding: FragmentEmployeeDetailBinding
        get() = checkNotNull(_binding) {
            "Are you sure the view is visible"
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEmployeeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { handleViewStateUpdate(it) }
            }
        }
    }

    private fun handleViewStateUpdate(state: EmployeeDetailViewState) {
        when (state) {
            is Loading -> showLoading()
            is EmployeesFetched -> showEmployee(state)
        }
    }

    private fun showLoading() {
        binding.apply {
            shimmerLayout.visibility = View.VISIBLE

            photo.visibility = View.GONE
            name.visibility = View.GONE
            bio.visibility = View.GONE
        }
    }

    private fun showEmployee(state: EmployeesFetched) {
        binding.apply {
            shimmerLayout.visibility = View.GONE

            photo.visibility = View.VISIBLE
            photo.load(state.employee.photoUrlLarge) {
                fallback(R.drawable.place_holder_image_homer_simpson)
                placeholder(R.drawable.place_holder_image_homer_simpson)
            }

            name.visibility = View.VISIBLE
            name.text = state.employee.fullName

            bio.visibility = View.VISIBLE
            bio.text = state.employee.biography
        }
    }
}