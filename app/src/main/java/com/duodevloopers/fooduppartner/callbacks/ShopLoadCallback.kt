package com.duodevloopers.fooduppartner.callbacks

import com.google.firebase.firestore.DocumentReference

interface ShopLoadCallback {
    fun onSuccess(databaseReference: DocumentReference)
    fun onFailure()
}