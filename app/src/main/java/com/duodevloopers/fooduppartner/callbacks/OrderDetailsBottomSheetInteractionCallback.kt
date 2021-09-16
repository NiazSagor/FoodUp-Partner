package com.duodevloopers.fooduppartner.callbacks

import com.duodevloopers.fooduppartner.model.FoodOrder

interface OrderDetailsBottomSheetInteractionCallback {
    fun onOrderReady(model: FoodOrder)
    fun onCancel()
}