package com.duodevloopers.fooduppartner.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.duodevloopers.fooduppartner.R
import com.duodevloopers.fooduppartner.model.Partner
import com.duodevloopers.fooduppartner.viewmodels.MainActivityViewModel
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fragment_registration.*


class RegistrationFragment : Fragment(R.layout.fragment_registration), View.OnClickListener {

    private val model: MainActivityViewModel by activityViewModels()

    private lateinit var uri: Uri
    private lateinit var downloadUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        thumbnail.setOnClickListener {
            com.github.dhaval2404.imagepicker.ImagePicker.with(this)
                .crop()
                .compress(1024)
                .maxResultSize(1080, 1080)
                .start()
        }

        val adapter: ArrayAdapter<String> =
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                arrayListOf("Food", "Stationery")
            )

        store_type.setAdapter(adapter)

        store_type.setText(model.getType())

        button_register.setOnClickListener(this)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            uri = data?.data!!
            animationView.visibility = View.VISIBLE
            thumbnail.setImageURI(uri)
            uploadImage(uri)
        }
    }

    override fun onClick(v: View?) {
        if (edit_text_shop_details.text.toString() == ""
            ||
            edit_text_shop_name.text.toString() == ""
            ||
            edit_text_shop_owner_name.text.toString() == ""
            ||
            edit_text_shop_phone.text.toString() == ""
        ) {
            Toast.makeText(requireContext(), "Fields must not be empty", Toast.LENGTH_SHORT).show()
        } else {

            animationView.visibility = View.VISIBLE

            model.set(
                Partner(
                    edit_text_shop_details.text.toString(),
                    downloadUrl,
                    edit_text_shop_name.text.toString(),
                    edit_text_shop_owner_name.text.toString(),
                    edit_text_shop_phone.text.toString(),
                    model.getType()
                )
            )

            model.setPartnerToDb()

            Handler().postDelayed({
                animationView.visibility = View.GONE
                findNavController().navigate(RegistrationFragmentDirections.actionRegistrationFragmentToHomeFragment())
            }, 1200)
        }
    }

    private fun uploadImage(uri: Uri) {
        val storageReference = FirebaseStorage.getInstance()
            .getReference("Store Cover Image")

        storageReference.putFile(uri)
            .addOnCompleteListener {
                animationView.visibility = View.GONE
                if (it.isSuccessful) {
                    downloadUrl = it.result.toString()
                } else {
                    Toast.makeText(requireContext(), "Upload unsuccessful", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            .addOnFailureListener(OnFailureListener {
                animationView.visibility = View.GONE
                Toast.makeText(requireContext(), "Upload unsuccessful", Toast.LENGTH_SHORT).show()
            })

    }

}