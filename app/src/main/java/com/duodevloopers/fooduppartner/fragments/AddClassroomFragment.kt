package com.duodevloopers.fooduppartner.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.duodevloopers.fooduppartner.R
import com.duodevloopers.fooduppartner.model.Room
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_add_classroom.*

class AddClassroomFragment : Fragment(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_classroom, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter: ArrayAdapter<String> =
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                getDepartments()
            )

        department_title_textview.setAdapter(adapter)

        create_room.setOnClickListener(this)

    }

    private fun getDepartments(): ArrayList<String> = arrayListOf(
        "CSE",
        "EEE",
        "ELL",
        "LLB",
        "Pharmacy",
        "SHIS",
        "ENGLISH",
        "QSIS",
    )

    override fun onClick(v: View?) {

        val room = Room(
            editTextRoomNo.text.toString(),
            "",
            "",
            true
        )

        FirebaseFirestore.getInstance()
            .collection(department_title_textview.text.toString().lowercase() + "-" + "building")
            .document(editTextRoomNo.text.toString())
            .set(room)
            .addOnSuccessListener(OnSuccessListener {
                Toast.makeText(
                    requireContext(),
                    "Classroom created successfully",
                    Toast.LENGTH_SHORT
                ).show()
            })
    }
}