package com.duodevloopers.fooduppartner.fragments

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.duodevloopers.fooduppartner.R
import com.duodevloopers.fooduppartner.viewmodels.MainActivityViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_select_type.*


class SelectTypeFragment : Fragment(R.layout.fragment_select_type) {


    private val model: MainActivityViewModel by activityViewModels()

    private lateinit var selectedUserType: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        selectedUserType = "teacher"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        stationary.setOnClickListener {
            selectedUserType = "stationery"
            model.setType("stationery")
            stationary.background = ContextCompat.getDrawable(
                requireContext(),
                R.drawable.selected
            )
            restaurant.background = null
        }

        restaurant.setOnClickListener {
            selectedUserType = "food"
            model.setType("food")
            restaurant.background = ContextCompat.getDrawable(
                requireContext(),
                R.drawable.selected
            )
            stationary.background = null
        }

        teacher.setOnClickListener {
            selectedUserType = "teacher"
            model.setType("teacher")
            teacher.background = ContextCompat.getDrawable(
                requireContext(),
                R.drawable.selected
            )
            teacher.background = null
        }

        next_button.setOnClickListener {
            animationView.visibility = View.VISIBLE
            when (selectedUserType) {
                "food", "stationery" -> checkIfShopUserExist()
                else -> checkIfTeacherExists()
            }
        }
    }

    private fun checkIfShopUserExist() {

        val number = FirebaseAuth.getInstance().currentUser?.phoneNumber

        FirebaseFirestore.getInstance()
            .collection("shops")
            .get()
            .addOnSuccessListener {
                for (partner in it.documents) {
                    if (partner["phoneNumber"] == number) {
                        animationView.visibility = View.GONE
                        findNavController().navigate(
                            SelectTypeFragmentDirections.actionSelectTypeFragmentToHomeFragment()
                        )
                        return@addOnSuccessListener
                    }
                }

                animationView.visibility = View.GONE
                when (selectedUserType) {
                    "stationery", "food" -> {
                        findNavController().navigate(
                            SelectTypeFragmentDirections.actionSelectTypeFragmentToRegistrationFragment()
                        )
                    }
                }
            }

    }

    private fun checkIfTeacherExists() {

        val number = FirebaseAuth.getInstance().currentUser?.phoneNumber

        FirebaseFirestore.getInstance()
            .collection("teacher")
            .get()
            .addOnSuccessListener {
                for (teacher in it.documents) {
                    if (teacher["number"] == number) {
                        animationView.visibility = View.GONE
                        findNavController().navigate(
                            SelectTypeFragmentDirections.actionSelectTypeFragmentToTeacherHomeFragment()
                        )
                        return@addOnSuccessListener
                    }
                }

                animationView.visibility = View.GONE

                findNavController().navigate(
                    SelectTypeFragmentDirections.actionSelectTypeFragmentToTeacherRegistration()
                )
            }

    }


}