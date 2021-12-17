package com.duodevloopers.fooduppartner.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.duodevloopers.fooduppartner.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_create_cr.*


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

            animationView.visibility = View.VISIBLE

            makeCr(cr_id.text.toString())

            Toast.makeText(requireContext(), "CR Created", Toast.LENGTH_SHORT).show()
        }
    }

    private fun makeCr(id: String) {
        FirebaseFirestore.getInstance()
            .collection("student").whereEqualTo("id", id)
            .get()
            .addOnSuccessListener {
                animationView.visibility = View.GONE
                if (!it.isEmpty) {
                    val doc = it.documents[0]
                    val docData = doc.data
                    docData?.set("type", "cr")
                    FirebaseFirestore.getInstance()
                        .collection("student")
                        .document(doc.reference.id)
                        .update(docData!!)
                        .addOnSuccessListener {
                            Toast.makeText(requireContext(), "CR Created", Toast.LENGTH_SHORT)
                                .show()
                        }
                } else {
                    Toast.makeText(requireContext(), "Student not found", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener {
                animationView.visibility = View.GONE
            }
    }

}