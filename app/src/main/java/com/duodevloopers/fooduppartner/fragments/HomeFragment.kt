package com.duodevloopers.fooduppartner.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.duodevloopers.fooduppartner.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        create_recharge.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToRechargeFragment()
            findNavController().navigate(action)
        }

        // if service order
        orders.setOnClickListener(View.OnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToServiceOrderFragment()
            findNavController().navigate(action)
        })

        // if food order
//        orders.setOnClickListener(View.OnClickListener {
//            val action = HomeFragmentDirections.actionHomeFragmentToFoodOrderFragment()
//            findNavController().navigate(action)
//        })
    }

}