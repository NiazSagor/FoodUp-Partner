package com.duodevloopers.fooduppartner.fragments

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.duodevloopers.fooduppartner.R
import com.duodevloopers.fooduppartner.viewmodels.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_teacher_home.*


class TeacherHomeFragment : Fragment(R.layout.fragment_teacher_home), View.OnClickListener {

    private val model: MainActivityViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        create_cr.setOnClickListener(this)
        create_attendance.setOnClickListener(this)
        class_status_layout.setOnClickListener(this)

        val drawable: Drawable = ContextCompat.getDrawable(requireContext(), R.drawable.circle)!!
        val wrappedDrawable = DrawableCompat.wrap(drawable)
        DrawableCompat.setTint(wrappedDrawable, Color.GREEN)
        class_status_image.setImageDrawable(wrappedDrawable)

        class_status.visibility = View.GONE


        model.getOccupiedRoomByTeacher().observe(viewLifecycleOwner, Observer {
            val drawable1: Drawable =
                ContextCompat.getDrawable(requireContext(), R.drawable.circle)!!
            val wrappedDrawable1 = DrawableCompat.wrap(drawable1)
            DrawableCompat.setTint(wrappedDrawable1, Color.RED)
            class_status_image.setImageDrawable(wrappedDrawable1)

            class_status.visibility = View.VISIBLE
            class_status.text = "Class : " + it
            val image = class_status_image as ImageView

            val animation: Animation =
                AnimationUtils.loadAnimation(requireContext(), R.anim.animation_list)
            image.startAnimation(animation)
        })

    }

    override fun onClick(v: View?) {
        when {
            v?.id?.equals(R.id.create_cr) == true -> {
                val action =
                    TeacherHomeFragmentDirections.actionTeacherHomeFragmentToCreateCrFragment()
                findNavController().navigate(action)
            }
            v?.id?.equals(R.id.create_attendance) == true -> {
                val action =
                    TeacherHomeFragmentDirections.actionTeacherHomeFragmentToCreateAttendanceFragment()
                findNavController().navigate(action)
            }
            else -> {
                val action =
                    TeacherHomeFragmentDirections.actionTeacherHomeFragmentToClassRoomFragment()
                findNavController().navigate(action)
            }
        }
    }

}