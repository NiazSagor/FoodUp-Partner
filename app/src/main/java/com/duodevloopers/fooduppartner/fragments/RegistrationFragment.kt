package com.duodevloopers.fooduppartner.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.duodevloopers.fooduppartner.R
import com.duodevloopers.fooduppartner.model.Partner
import com.duodevloopers.fooduppartner.viewmodels.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_registration.*

class RegistrationFragment : Fragment(R.layout.fragment_registration), View.OnClickListener {

    private val model: MainActivityViewModel by activityViewModels()

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

//        next_button.setOnClickListener(this)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val uri: Uri = data?.data!!
            thumbnail.setImageURI(uri)
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
            model.set(
                Partner(
                    edit_text_shop_details.text.toString(),
                    "",
                    edit_text_shop_name.text.toString(),
                    edit_text_shop_owner_name.text.toString(),
                    edit_text_shop_phone.text.toString(),
                    model.getType()
                )
            )

            // TODO: 16/11/2021 go to dashboard

            Toast.makeText(requireContext(), "Registration Completed", Toast.LENGTH_SHORT).show()
        }
    }

}