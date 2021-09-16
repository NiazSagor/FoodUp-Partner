package com.duodevloopers.fooduppartner.bottomsheets

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.duodevloopers.fooduppartner.R
import com.duodevloopers.fooduppartner.callbacks.OrderDetailsBottomSheetInteractionCallback
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton

class OrderDetailsBottomSheet(
    private val context: Context,
    private val orderDetailsBottomSheetInteractionCallback: OrderDetailsBottomSheetInteractionCallback
) : View.OnClickListener {

    private val bottomSheetDialog: BottomSheetDialog =
        BottomSheetDialog(context, R.style.BottomSheetDialogTheme)

    private val bottomSheetView: View =
        LayoutInflater.from(context).inflate(R.layout.video_section_fragment_list_item, null)

    private val itemList: RecyclerView = bottomSheetView.findViewById(R.id.order_list)
    private val ready: MaterialButton = bottomSheetView.findViewById(R.id.ready)
    private val cancel: MaterialButton = bottomSheetView.findViewById(R.id.cancel)

    fun showBottomSheet() {
        bottomSheetDialog.setContentView(bottomSheetView)
        bottomSheetDialog.setCancelable(true)

        ready.setOnClickListener(this)
        cancel.setOnClickListener(this)

        bottomSheetDialog.show()
    }

    fun hideBottomSheet() = bottomSheetDialog.dismiss()


    override fun onClick(v: View?) {
        when (v?.id) {
            ready.id -> orderDetailsBottomSheetInteractionCallback.onOrderReady()
            else -> orderDetailsBottomSheetInteractionCallback.onCancel()
        }
    }
}