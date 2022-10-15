package com.example.employeedirectory.presentation.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.employeedirectory.databinding.FragmentEmployeeDetailBinding
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "EmployeeDetailFragment"

@AndroidEntryPoint
class EmployeeDetailFragment : Fragment() {

    private val args: EmployeeDetailFragmentArgs by navArgs()

    private var _binding: FragmentEmployeeDetailBinding? = null
    private val binding: FragmentEmployeeDetailBinding
        get() = checkNotNull(_binding) {
            "Are you sure the view is visible"
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "The crime ID is: ${args.employeeID}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEmployeeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


}