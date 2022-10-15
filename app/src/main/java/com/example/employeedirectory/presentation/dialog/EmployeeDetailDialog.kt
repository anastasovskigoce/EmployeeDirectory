package com.example.employeedirectory.presentation.dialog

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.employeedirectory.R

private const val TAG = "EmployeeDetailDialog"

class EmployeeDetailDialog : DialogFragment() {

    private val args: EmployeeDetailDialogArgs by navArgs()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val photoUrl = args.imageSrc
        val bio = args.bio
        val name = args.name

        Log.d(TAG, "Creating dialog url = $photoUrl, name = $name, bio = $bio")

        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.fragment_employee_detail)
        dialog.findViewById<ImageView>(R.id.photo).load(photoUrl) {
            fallback(R.drawable.place_holder_image_homer_simpson)
            placeholder(R.drawable.place_holder_image_homer_simpson)
        }

        dialog.findViewById<TextView>(R.id.name).text = name
        dialog.findViewById<TextView>(R.id.bio).text = bio

        return dialog
    }
}