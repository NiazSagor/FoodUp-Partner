package com.duodevloopers.fooduppartner

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

class MainActivityViewModel : ViewModel {

    private lateinit var type: String

    private var partner: Partner = Partner("", "", "", "", "", "")

    fun set(partner: Partner) {
        this.partner = partner
        setPartnerToDb()
    }

    fun setType(type: String) {
        this.type = type
    }

    fun getType(): String {
        return type
    }

    private fun setPartnerToDb() {
        FirebaseFirestore.getInstance()
            .collection("partner")
            .document(partner.getPhoneNumber())
            .set(partner);
    }

    constructor()
}