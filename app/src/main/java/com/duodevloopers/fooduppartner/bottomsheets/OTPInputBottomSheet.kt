package com.duodevloopers.fooduppartner.bottomsheets

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.duodevloopers.fooduppartner.R
import com.duodevloopers.fooduppartner.callbacks.OTPInputBottomSheetInteractionCallback
import com.google.android.material.bottomsheet.BottomSheetDialog

class OTPInputBottomSheet(
    private val context: Context,
    private val OTPInputBottomSheetInteractionCallback: OTPInputBottomSheetInteractionCallback
) : View.OnClickListener {

    private val bottomSheetDialog: BottomSheetDialog =
        BottomSheetDialog(context, R.style.BottomSheetDialogTheme)

    private val bottomSheetView: View =
        LayoutInflater.from(context).inflate(R.layout.mobile_number_bottom_sheet, null)

    private val number: EditText = bottomSheetView.findViewById(R.id.et_phone_number)
    private val submit: Button = bottomSheetView.findViewById(R.id.btn_submit_phone_number)

    fun showBottomSheet() {
        bottomSheetDialog.setContentView(bottomSheetView)
        bottomSheetDialog.setCancelable(true)
        bottomSheetDialog.show()
    }

    fun hideBottomSheet() = bottomSheetDialog.dismiss()

    override fun onClick(v: View?) {
        when (v?.id) {
            submit.id -> {
                if (number.text.toString().isNotEmpty())
                    OTPInputBottomSheetInteractionCallback.onNumberSubmitted(number.text.toString())
                hideBottomSheet()
            }
        }
    }
}