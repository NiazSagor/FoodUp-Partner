package com.duodevloopers.fooduppartner.fragments

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.duodevloopers.fooduppartner.CaptureAct
import com.duodevloopers.fooduppartner.R
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import com.google.zxing.qrcode.QRCodeWriter
import com.journeyapps.barcodescanner.BarcodeEncoder
import kotlinx.android.synthetic.main.fragment_create_attendance.*
import kotlinx.android.synthetic.main.fragment_recharge.*
import kotlinx.android.synthetic.main.fragment_registration.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class RechargeFragment : Fragment(R.layout.fragment_recharge) {

    val myCalendar = Calendar.getInstance()

    companion object{
        private const val TAG = "RechargeFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val date =
            DatePickerDialog.OnDateSetListener { datePicker, year, monthOfYear, dayOfMonth ->
                myCalendar.set(Calendar.YEAR, year)
                myCalendar.set(Calendar.MONTH, monthOfYear)
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateLabel()
            }

        recharge_date.setOnClickListener {
            DatePickerDialog(
                requireContext(), date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }


        create_recharge_button.setOnClickListener {
            createQRCode()
        }
    }

    fun createQRCode () {
        if (id_number.text.toString() == ""
            ||
            recharge_amount.text.toString() == ""
            ||
            recharge_date.text.toString() == ""
            ||
            id_number.text.toString() == ""
        ) {
            Toast.makeText(requireContext(), "Fields must not be empty", Toast.LENGTH_SHORT).show()
        } else {
            val qrCode = id_number.text.toString().trim() +"/"+ recharge_amount.text.toString().trim() +"/"+ recharge_date.text.toString().trim()
            Log.d(TAG, "createQRCode: "+qrCode)
            val writer = QRCodeWriter()
            try {
                val matrix = writer.encode(qrCode, BarcodeFormat.QR_CODE,200,200)
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
        recharge_date.setText(sdf.format(myCalendar.time))
    }
}