package com.duodevloopers.fooduppartner.fragments

import android.app.Activity
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Bitmap.createBitmap
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.DatePicker
import android.widget.Toast
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
import kotlinx.android.synthetic.main.fragment_recharge.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class CreateAttendanceFragment : Fragment(R.layout.fragment_create_attendance) {

    val myCalendar = Calendar.getInstance()

    companion object {
        private const val TAG = "CreateAttendanceFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val date =
            OnDateSetListener { datePicker, year, monthOfYear, dayOfMonth ->
                myCalendar.set(Calendar.YEAR, year)
                myCalendar.set(Calendar.MONTH, monthOfYear)
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateLabel()
            }

            date_of_attendance.setOnClickListener {
                DatePickerDialog(
                    requireContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }

        create_attendance.setOnClickListener {
            createQRCode()
        }
    }


    fun createQRCode () {
        if (course_code.text.toString() == ""
            ||
            course_title.text.toString() == ""
            ||
            section.text.toString() == ""
            ||
            date_of_attendance.text.toString() == ""
            ||
            number_of_class.text.toString() == ""
        ) {
            Toast.makeText(requireContext(), "Fields must not be empty", Toast.LENGTH_SHORT).show()
        } else {
            val courseCode = course_code.text.toString().trim() +"/"+ section.text.toString().trim()
            Log.d(TAG, "createQRCode: "+ courseCode)
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

    private fun updateLabel() {
        val myFormat = "MM/dd/yy" //In which you need put here
        val sdf: DateFormat = SimpleDateFormat(myFormat, Locale.US)
        date_of_attendance.setText(sdf.format(myCalendar.time))
    }

}