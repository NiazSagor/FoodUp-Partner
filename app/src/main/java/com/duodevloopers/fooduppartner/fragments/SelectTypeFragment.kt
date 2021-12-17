package com.duodevloopers.fooduppartner.fragments

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.duodevloopers.fooduppartner.R
import com.duodevloopers.fooduppartner.viewmodels.MainActivityViewModel
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

            val action = when (selectedUserType) {
                "stationery" -> SelectTypeFragmentDirections.actionSelectTypeFragmentToRegistrationFragment()
                "food" -> SelectTypeFragmentDirections.actionSelectTypeFragmentToRegistrationFragment()
                else -> SelectTypeFragmentDirections.actionSelectTypeFragmentToTeacherLogin()
            }

            findNavController().navigate(action)
        }
    }


}