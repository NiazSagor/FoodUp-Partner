package com.duodevloopers.fooduppartner.fragments

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.duodevloopers.fooduppartner.R
import com.duodevloopers.fooduppartner.adapter.RoomAdapter
import com.duodevloopers.fooduppartner.callbacks.OnRoomSelectedListener
import com.duodevloopers.fooduppartner.model.Room
import com.duodevloopers.fooduppartner.viewmodels.MainActivityViewModel
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.fragment_class_room.*


class ClassRoomFragment : Fragment(), OnRoomSelectedListener {

    private val model: MainActivityViewModel by activityViewModels()

    private lateinit var adapter: RoomAdapter

    private var selectedDept: String = "cse-building"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_class_room, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.setNavigationOnClickListener {

        }

        getRooms(selectedDept)

        chip_group.setOnCheckedChangeListener(ChipGroup.OnCheckedChangeListener { group, checkedId ->

            val chip: Chip = group.findViewById(checkedId)

            selectedDept = chip.text.toString().toLowerCase() + "-" + "building"

            getRooms(selectedDept)
        })


    }

    private fun getRooms(building: String) {
        val query: Query = FirebaseFirestore.getInstance()
            .collection(building).orderBy("status")
        adapter = RoomAdapter(
            FirestoreRecyclerOptions.Builder<Room>()
                .setQuery(query, Room::class.java)
                .build()
        )

        room_recycler_view.layoutManager = GridLayoutManager(context, 4)

        room_recycler_view.adapter = adapter
        adapter.setOnRoomSelectedListener(this)
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }

    override fun onSelected(roomNo: String) {
        showAlertDialog(roomNo)
    }

    private fun showAlertDialog(roomNo: String) {
        val builder = AlertDialog.Builder(requireContext());
        builder.setMessage("Confirm to mark " + roomNo + " room as unavailable?")
            .setCancelable(false)
            .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, id ->
                model.getOccupiedRoomByTeacher().value = roomNo
                markRoomAsUnavailable(roomNo)
            })
            .setNegativeButton(
                "No",
                DialogInterface.OnClickListener { dialog, id -> //  Action for 'NO' Button
                    dialog.cancel()
                })
        val alert: AlertDialog = builder.create()
        alert.setTitle("Confirmation")
        alert.show()
    }

    private fun markRoomAsUnavailable(roomNo: String) {
        FirebaseFirestore.getInstance()
            .collection(selectedDept)
            .document(roomNo)
            .update("status", false)
            .addOnSuccessListener(OnSuccessListener {
                Toast.makeText(requireContext(), "Room is marked unavailable", Toast.LENGTH_SHORT)
                    .show()
            })
    }
}