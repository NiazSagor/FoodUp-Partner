package com.duodevloopers.fooduppartner.model

data class FoodItem(
    val mFoodName: String,
    val mImage: String,
    val mFoodDes: String,
    val mFoodPrice: Int,
    val quantity: Int,
    val shopName: String
) {
}