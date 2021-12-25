package com.duodevloopers.fooduppartner.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.duodevloopers.fooduppartner.model.Partner
import com.google.firebase.firestore.FirebaseFirestore

class MainActivityViewModel : ViewModel {

    private lateinit var type: String

    private var partner: Partner = Partner("", "", "", "", "", "")

    fun set(partner: Partner) {
        this.partner = partner
    }

    fun getPartner(): Partner = partner

    fun setType(type: String) {
        this.type = type
    }

    fun getType(): String {
        return type
    }

    fun setPartnerToDb() {
        FirebaseFirestore.getInstance()
            .collection("shops")
            .document(partner.getPhoneNumber())
            .set(partner)
    }

    private var occupiedRoomByTeacher: MutableLiveData<String> = MutableLiveData<String>()

    fun getOccupiedRoomByTeacher(): MutableLiveData<String> = occupiedRoomByTeacher

    constructor()
}