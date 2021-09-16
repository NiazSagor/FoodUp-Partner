package com.duodevloopers.fooduppartner.callbacks

import com.duodevloopers.fooduppartner.model.FoodOrder

interface OrderDetailsBottomSheetInteractionCallback {
    fun onClick(items: List<String>)
    fun onOrderReady(model: FoodOrder)
    fun onCancel()
}