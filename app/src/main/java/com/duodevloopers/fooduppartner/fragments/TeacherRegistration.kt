package com.duodevloopers.fooduppartner.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.duodevloopers.fooduppartner.databinding.TeacherRegistrationBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class TeacherRegistration : Fragment() {
    private lateinit var binding: TeacherRegistrationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TeacherRegistrationBinding.inflate(
            layoutInflater
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cirRegisterButton.setOnClickListener {
            if (binding.editTextDesignation.text.toString() != "" &&
                binding.editTextDepartment.text.toString() != "" &&
                binding.editTextEmail.text.toString() != "" &&
                binding.editTextName.text.toString() != "" &&
                binding.editTextId.text.toString() != ""
            ) {
                binding.animationView.visibility = View.VISIBLE
                val map: MutableMap<String, String> = HashMap()
                map["department"] = binding.editTextDepartment.text.toString()
                map["designation"] = binding.editTextDesignation.text.toString()
                map["id"] = binding.editTextId.text.toString()
                map["name"] = binding.editTextName.text.toString()
                map["email"] = binding.editTextEmail.text.toString()
                FirebaseFirestore.getInstance()
                    .collection("teacher")
                    .document()
                    .set(map)
                    .addOnSuccessListener {
                        binding.animationView.visibility = View.GONE
                        Toast.makeText(
                            requireContext(),
                            "Registration successful",
                            Toast.LENGTH_SHORT
                        ).show()
                    }.addOnFailureListener {
                        Toast.makeText(
                            requireContext(),
                            "Something went wrong",
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.animationView.visibility = View.GONE
                    }
                findNavController().navigate(
                    TeacherRegistrationDirections.actionTeacherRegistrationToTeacherHomeFragment()
                )
            } else {
                binding.animationView.visibility = View.GONE
                Toast.makeText(requireContext(), "Please fill all the fields", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}