package com.example.employeedirectory.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.employeedirectory.R
import com.example.employeedirectory.databinding.EmployeeDetailFragmentBinding
import com.example.employeedirectory.databinding.EmployeeDirectoryListFragmentBinding
import kotlinx.coroutines.launch

class EmployeeDetailFragment : Fragment() {

    private var _binding: EmployeeDetailFragmentBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    private val args: EmployeeDetailFragmentArgs by navArgs()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = EmployeeDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.name.text = args.name
        binding.bio.text = args.bio
        binding.photo.load(args.largePhotoURL) {
            fallback(R.drawable.place_holder_image_homer_simpson)
            placeholder(R.drawable.place_holder_image_homer_simpson)
        }
    }
}