package com.duodevloopers.fooduppartner.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.duodevloopers.fooduppartner.R
import kotlinx.android.synthetic.main.fragment_teacher_home.*

class TeacherHomeFragment : Fragment(R.layout.fragment_teacher_home) , View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        create_cr_button.setOnClickListener(this)
        create_attendance_button.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        if(v?.id?.equals(R.id.create_cr_button) == true) {
            val action =
                TeacherHomeFragmentDirections.actionTeacherHomeFragmentToCreateCrFragment()
            findNavController().navigate(action)
        }
        else {
            val action =
                TeacherHomeFragmentDirections.actionTeacherHomeFragmentToCreateAttendanceFragment()
            findNavController().navigate(action)
        }
    }

}