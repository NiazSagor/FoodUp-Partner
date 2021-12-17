package com.duodevloopers.fooduppartner.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.duodevloopers.fooduppartner.R
import com.duodevloopers.fooduppartner.viewmodels.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val model: MainActivityViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        create_recharge.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToRechargeFragment())
        }

        orders.setOnClickListener {

            val action = when (model.getType()) {
                "stationery" -> HomeFragmentDirections.actionHomeFragmentToServiceOrderFragment()
                else -> HomeFragmentDirections.actionHomeFragmentToFoodOrderFragment()
            }

            findNavController().navigate(action)
        }
    }

}