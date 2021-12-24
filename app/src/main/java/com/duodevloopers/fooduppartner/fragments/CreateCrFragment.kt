package com.duodevloopers.fooduppartner.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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

        cr_id.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s!!.length == 7) ifCr(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
                //ifCr(s.toString())
            }

        })

        create_cr.setOnClickListener {
            if (create_cr.text.equals("Create")) {
                createCR()
            } else if (create_cr.text.equals("Remove CR")) {
                makeCr(cr_id.text.toString(), false)
            }
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

            makeCr(cr_id.text.toString(), true)

            Toast.makeText(requireContext(), "CR Created", Toast.LENGTH_SHORT).show()
        }
    }

    private fun makeCr(id: String, ifNewCR: Boolean) {
        FirebaseFirestore.getInstance()
            .collection("student").whereEqualTo("id", id)
            .get()
            .addOnSuccessListener {
                animationView.visibility = View.GONE
                if (!it.isEmpty) {
                    val doc = it.documents[0]
                    val docData = doc.data
                    if (ifNewCR) docData?.set("type", "cr") else docData?.set("type", "student")
                    val msg = if (ifNewCR) "CR Created" else "CR Removed"
                    FirebaseFirestore.getInstance()
                        .collection("student")
                        .document(doc.reference.id)
                        .update(docData!!)
                        .addOnSuccessListener {
                            Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT)
                                .show()
                        }
                } else {
                    Toast.makeText(requireContext(), "Student not found", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener {
                animationView.visibility = View.GONE
            }
    }

    private fun ifCr(id: String) {
        FirebaseFirestore.getInstance()
            .collection("student")
            .whereEqualTo("id", id)
            .get()
            .addOnSuccessListener {
                if (!it.isEmpty) {
                    val doc = it.documents[0]
                    val docData = doc.data
                    if (docData!!["type"] == "cr") {
                        already_cr_text.visibility = View.VISIBLE
                        create_cr.text = "Remove CR"
                    } else {
                        already_cr_text.visibility = View.GONE
                        create_cr.text = "Create"
                    }
                } else {
                    create_cr.text = "Create"
                    already_cr_text.visibility = View.GONE
                }
            }
    }

}