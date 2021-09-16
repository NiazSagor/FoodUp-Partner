package com.duodevloopers.fooduppartner.model

class Partner(
    private var details: String,
    private var image: String,
    private var name: String,
    private var ownerName: String,
    private var phoneNumber: String,
    private var type: String
) {

    fun getPhoneNumber(): String {
        return phoneNumber
    }
}