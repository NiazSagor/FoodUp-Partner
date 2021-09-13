package com.duodevloopers.fooduppartner

import android.app.Activity
import android.content.Intent
import android.icu.number.NumberFormatter.with
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.github.dhaval2404.imagepicker.ImagePicker.Companion.RESULT_ERROR
import com.github.dhaval2404.imagepicker.ImagePicker.Companion.with
import com.google.android.gms.cast.framework.media.ImagePicker
import com.karumi.dexter.listener.multi.SnackbarOnAnyDeniedMultiplePermissionsListener.Builder.with
import com.karumi.dexter.listener.multi.SnackbarOnAnyPermanentlyDeniedMultiplePermissionsListener.Builder.with
import kotlinx.android.synthetic.main.fragment_registration.*
import java.net.URI

class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        thumbnail.setOnClickListener {
            com.github.dhaval2404.imagepicker.ImagePicker.with(this)
                .crop()
                .compress(1024)
                .maxResultSize(1080,1080)
                .start()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK) {
            val uri:Uri = data?.data!!
            thumbnail.setImageURI(uri)
        }
    }

}