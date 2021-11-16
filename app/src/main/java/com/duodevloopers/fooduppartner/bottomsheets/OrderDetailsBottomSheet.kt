package com.duodevloopers.fooduppartner.bottomsheets

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.duodevloopers.fooduppartner.R
import com.duodevloopers.fooduppartner.adapter.ItemAdapter
import com.duodevloopers.fooduppartner.callbacks.OrderDetailsBottomSheetInteractionCallback
import com.duodevloopers.fooduppartner.model.FoodOrder
import com.google.android.material.bottomsheet.BottomSheetDialog

class OrderDetailsBottomSheet(
    private val context: Context,
    private val orderDetailsBottomSheetInteractionCallback: OrderDetailsBottomSheetInteractionCallback
) : View.OnClickListener {

    private val TAG = "OrderDetailsBottomSheet"

    private lateinit var model: FoodOrder

    private val bottomSheetDialog: BottomSheetDialog =
        BottomSheetDialog(context, R.style.BottomSheetDialogTheme)

    private val bottomSheetView: View =
        LayoutInflater.from(context).inflate(R.layout.details_bottom_sheet_layout, null)

    private val itemList: RecyclerView = bottomSheetView.findViewById(R.id.item_list)
    private val ready: TextView = bottomSheetView.findViewById(R.id.ready)
    private val cancel: TextView = bottomSheetView.findViewById(R.id.cancel)

    private lateinit var adapter: ItemAdapter


    fun showBottomSheet(model: FoodOrder) {
        this.model = model

        var list =
            "Chicken Biriyani#Chicken Dum Biriyani#Sub Sandwich#Chicken Polao#Chicken Nuggets#French Fries".split(
                "#"
            )

        adapter = ItemAdapter(list)
        itemList.adapter = adapter

        bottomSheetDialog.setContentView(bottomSheetView)
        bottomSheetDialog.setCancelable(true)

        ready.setOnClickListener(this)
        cancel.setOnClickListener(this)

        bottomSheetDialog.show()
    }

    fun hideBottomSheet() = bottomSheetDialog.dismiss()


    override fun onClick(v: View?) {
        when (v?.id) {
            ready.id -> orderDetailsBottomSheetInteractionCallback.onOrderReady(model)
            else -> orderDetailsBottomSheetInteractionCallback.onCancel()
        }
    }
}