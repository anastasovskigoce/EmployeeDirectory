package com.example.employeedirectory.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.employeedirectory.R
import com.example.employeedirectory.data.Employee
import com.example.employeedirectory.databinding.EmployeeItemListBinding

class EmployeeDirectoryViewHolder(
    private val binding: EmployeeItemListBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(employee: Employee, onEmployeeClicked: (largePhotoUrl: String, bio: String, name: String) -> Unit) {
        binding.name.text = employee.fullName
        binding.team.text = employee.team
        binding.photo.load(employee.photoUrlSmall) {
            fallback(R.drawable.place_holder_image_homer_simpson)
            placeholder(R.drawable.place_holder_image_homer_simpson)
        }

        binding.root.setOnClickListener {
            //TODO do this better
            if (employee.photoUrlLarge != null && employee.biography != null && employee.fullName != null){
                onEmployeeClicked(employee.photoUrlLarge, employee.biography, employee.fullName)
            }
        }
    }
}

class EmployeeDirectoryListAdapter(
    private val employees: List<Employee>,
    private val onEmployeeClicked: (largePhotoUrl: String, bio: String, name: String) -> Unit
) : RecyclerView.Adapter<EmployeeDirectoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeDirectoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = EmployeeItemListBinding.inflate(inflater, parent, false)
        return EmployeeDirectoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmployeeDirectoryViewHolder, position: Int) {
        holder.bind(employees[position], onEmployeeClicked)
    }

    override fun getItemCount(): Int {
        return employees.size
    }
}