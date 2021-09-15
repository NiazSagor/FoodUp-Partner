package com.duodevloopers.fooduppartner

import com.google.firebase.firestore.DocumentReference

interface ShopLoadCallback {
    fun onSuccess(databaseReference: DocumentReference)
    fun onFailure()
}