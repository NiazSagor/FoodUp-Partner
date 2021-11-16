package com.duodevloopers.fooduppartner.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.duodevloopers.fooduppartner.R
import com.duodevloopers.fooduppartner.adapter.ServiceOrderAdapter
import com.duodevloopers.fooduppartner.callbacks.ShopLoadCallback
import com.duodevloopers.fooduppartner.clicklisteners.ServiceOnClickListener
import com.duodevloopers.fooduppartner.model.ServiceOrder
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.fragment_service_order.*


class ServiceOrderFragment : Fragment(), ShopLoadCallback, ServiceOnClickListener {

    private val TAG = "ServiceOrderFragment"

    private lateinit var shopLoadCallback: ShopLoadCallback

    private lateinit var adapter: ServiceOrderAdapter

    private lateinit var documentReference: DocumentReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        shopLoadCallback = this

        FirebaseFirestore.getInstance()
            .collection("shops")
            .document("21CHxdPcqDZOhT2xsQpi")
            .get()
            .addOnSuccessListener(OnSuccessListener {
                shopLoadCallback.onSuccess(it.reference)
            })
            .addOnFailureListener(OnFailureListener {
                shopLoadCallback.onFailure()
            })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_service_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onSuccess(databaseReference: DocumentReference) {

        documentReference = databaseReference

        // todo make index in db
        val query: Query = databaseReference.collection("orders")
            .whereEqualTo("done", false)

        val options = FirestoreRecyclerOptions.Builder<ServiceOrder>()
            .setQuery(query, ServiceOrder::class.java)
            .build()

        adapter = ServiceOrderAdapter(options)
        order_list.adapter = adapter
        adapter.startListening()
        adapter.setOnClickListener(this)
    }

    override fun onFailure() {
        Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }

    override fun onOpenLink(link: String) {
        Log.d(TAG, "onOpenLink: " + link)

        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(browserIntent)

//        PdfViewerActivity.launchPdfFromUrl(
//            requireActivity(),
//            link,
//            "File",
//            "",                  // If nothing specific, Put "" it will save to Downloads
//            enableDownload = true
//        )
    }

    override fun onMarkDone(model: ServiceOrder) {
        documentReference.collection("orders")
            .document(model.getId())
            .update("done", true)
            .addOnSuccessListener(OnSuccessListener {
                Toast.makeText(requireContext(), "Order is marked as completed", Toast.LENGTH_SHORT)
                    .show()
            })
    }

}