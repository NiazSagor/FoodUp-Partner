package com.duodevloopers.fooduppartner.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.duodevloopers.fooduppartner.R
import com.duodevloopers.fooduppartner.callbacks.OnRoomSelectedListener
import com.duodevloopers.fooduppartner.model.Room
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class RoomAdapter(options: FirestoreRecyclerOptions<Room>) :
    FirestoreRecyclerAdapter<Room, RoomAdapter.RoomViewHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        return RoomViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.room_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int, model: Room) {
        holder.roodNo.text = model.room
        if (!model.status) {
            holder.card.setBackgroundColor(Color.RED)
        } else {
            holder.card.setBackgroundColor(Color.GREEN)
        }

        holder.itemView.setOnClickListener {
            onRoomSelectedListener.onSelected(model.room)
        }
    }

    class RoomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val roodNo: TextView = itemView.findViewById(R.id.room_id)
        val card: ConstraintLayout = itemView.findViewById(R.id.card)
    }

    private lateinit var onRoomSelectedListener: OnRoomSelectedListener

    fun setOnRoomSelectedListener(onRoomSelectedListener: OnRoomSelectedListener) {
        this.onRoomSelectedListener = onRoomSelectedListener
    }
}