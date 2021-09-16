package com.duodevloopers.fooduppartner.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.duodevloopers.fooduppartner.R
import com.duodevloopers.fooduppartner.bottomsheets.OrderDetailsBottomSheet
import com.duodevloopers.fooduppartner.callbacks.OrderDetailsBottomSheetInteractionCallback
import com.duodevloopers.fooduppartner.callbacks.ShopLoadCallback
import com.duodevloopers.fooduppartner.clicklisteners.FoodOrderOnClickListener
import com.duodevloopers.fooduppartner.model.FoodOrder
import com.duodevloopers.fooduppartner.model.ServiceOrder
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class FoodOrderFragment : Fragment(), ShopLoadCallback, OrderDetailsBottomSheetInteractionCallback,
    FoodOrderOnClickListener {

    private lateinit var orderDetailsBottomSheet: OrderDetailsBottomSheet

    private lateinit var shopLoadCallback: ShopLoadCallback

    private lateinit var documentReference: DocumentReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        shopLoadCallback = this

        FirebaseFirestore.getInstance()
            .collection("shops")
            .document("")
            .get()
            .addOnSuccessListener(OnSuccessListener {
                shopLoadCallback.onSuccess(it.reference)
            })
            .addOnFailureListener(OnFailureListener {
                shopLoadCallback.onFailure()
            })

        orderDetailsBottomSheet = OrderDetailsBottomSheet(requireContext(), this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_food_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    override fun onSuccess(databaseReference: DocumentReference) {
        documentReference = databaseReference

        // todo make index in db
        val query: Query = databaseReference.collection("orders")
            .whereEqualTo("done", false).orderBy("timestamp")

        val options = FirestoreRecyclerOptions.Builder<ServiceOrder>()
            .setQuery(query, ServiceOrder::class.java)
            .build()

        //todo make adapter for food order same as service order adapter
    }

    override fun onFailure() {
        Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
//        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
//        adapter.stopListening()
    }

    override fun onClick(model: FoodOrder) {
        orderDetailsBottomSheet.showBottomSheet(model)
    }

    // from bottom sheet
    override fun onOrderReady(model: FoodOrder) {
        documentReference.collection("orders")
            .document(model.getId())
            .update("done", true)
            .addOnSuccessListener(OnSuccessListener {
                Toast.makeText(requireContext(), "Order is marked as completed", Toast.LENGTH_SHORT)
                    .show()
            })
    }

    // from bottom sheet
    override fun onCancel() {
        orderDetailsBottomSheet.hideBottomSheet()
    }

}