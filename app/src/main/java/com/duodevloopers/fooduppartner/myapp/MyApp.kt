package com.duodevloopers.fooduppartner.myapp

import android.app.Application
import com.duodevloopers.fooduppartner.model.Partner
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        getUser()
    }

    companion object {

        lateinit var partner: Partner

        fun getUser() {
            if (FirebaseAuth.getInstance().currentUser != null) {

                FirebaseFirestore.getInstance().collection("shops")
                    .document(FirebaseAuth.getInstance().currentUser?.phoneNumber.toString())
                    .get()
                    .addOnSuccessListener {
                        if (it.exists()) {
                            partner = it.toObject(Partner::class.java)!!
                        }
                    }


            }
        }

    }

}