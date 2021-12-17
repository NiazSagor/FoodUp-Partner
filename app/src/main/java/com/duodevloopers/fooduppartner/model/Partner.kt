package com.duodevloopers.fooduppartner.model

class Partner(
    var details: String,
    var image: String,
    var name: String,
    var ownerName: String,
    private var phoneNumber: String,
    var type: String
) {

    fun getPhoneNumber(): String {
        return phoneNumber
    }
}