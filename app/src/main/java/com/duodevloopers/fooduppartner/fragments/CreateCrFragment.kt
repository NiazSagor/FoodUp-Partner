package com.duodevloopers.fooduppartner.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.duodevloopers.fooduppartner.R
import com.duodevloopers.fooduppartner.model.Partner
import kotlinx.android.synthetic.main.fragment_create_cr.*
import kotlinx.android.synthetic.main.fragment_registration.*


class CreateCrFragment : Fragment(R.layout.fragment_create_cr) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        create_cr.setOnClickListener {
            createCR()
        }

    }

    fun createCR() {
        if (cr_name.text.toString() == ""
            ||
            cr_id.text.toString() == ""
            ||
            cr_sem.text.toString() == ""
            ||
            cr_section.text.toString() == ""
            ||
            cr_dept.text.toString() == ""
        ) {
            Toast.makeText(requireContext(), "Fields must not be empty", Toast.LENGTH_SHORT).show()
        } else {
                //do database operation
            Toast.makeText(requireContext(),"CR Created",Toast.LENGTH_SHORT).show()
        }
    }

}