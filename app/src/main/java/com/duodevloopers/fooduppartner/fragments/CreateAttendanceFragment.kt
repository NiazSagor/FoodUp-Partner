package com.duodevloopers.fooduppartner.fragments

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Bitmap.createBitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import com.duodevloopers.fooduppartner.R
import com.duodevloopers.fooduppartner.databinding.ActivityMainBinding.inflate
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter
import com.journeyapps.barcodescanner.BarcodeEncoder
import kotlinx.android.synthetic.main.fragment_create_attendance.*


class CreateAttendanceFragment : Fragment(R.layout.fragment_create_attendance) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        create_attendance.setOnClickListener {
            createQRCode()
        }
    }


    fun createQRCode () {
        val courseCode = course_code.text.toString().trim()
        val writer = QRCodeWriter()
        try {
            val matrix = writer.encode(courseCode,BarcodeFormat.QR_CODE,200,200)
            val encoder = BarcodeEncoder()
            val bitmap = encoder.createBitmap(matrix)
            qr_code.setImageBitmap(bitmap)
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(requireView()!!.getWindowToken(), 0)
        } catch (e : WriterException) {
            e.stackTrace
        }
    }

}