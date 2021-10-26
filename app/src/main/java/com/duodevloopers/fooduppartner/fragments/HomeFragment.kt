package com.duodevloopers.fooduppartner.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.duodevloopers.fooduppartner.R
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_recharge.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recharge.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToRechargeFragment()
            findNavController().navigate(action)
        }
    }

}