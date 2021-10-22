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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        stationary.setOnClickListener {
            model.setType("service")
            stationary.setBackground(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.selected
                )
            )
            restaurant.setBackground(null)
        }

        restaurant.setOnClickListener {
            model.setType("food")
            restaurant.setBackground(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.selected
                )
            )
            stationary.setBackground(null)
        }

        teacher.setOnClickListener {
            model.setType("teacher")
            teacher.setBackground(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.selected
                )
            )
            teacher.setBackground(null)
        }

        next_button.setOnClickListener {
            val action =
                SelectTypeFragmentDirections.actionSelectTypeFragmentToRegistrationFragment()
            findNavController().navigate(action)
        }
    }


}