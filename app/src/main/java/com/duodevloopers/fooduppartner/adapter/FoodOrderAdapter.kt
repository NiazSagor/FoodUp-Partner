package com.duodevloopers.fooduppartner.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.duodevloopers.fooduppartner.R
import com.duodevloopers.fooduppartner.clicklisteners.FoodOrderOnClickListener
import com.duodevloopers.fooduppartner.model.FoodOrder
import com.duodevloopers.fooduppartner.utility.Utility
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions


class FoodOrderAdapter(options: FirestoreRecyclerOptions<FoodOrder>) :
    FirestoreRecyclerAdapter<FoodOrder, FoodOrderAdapter.FoodOrderViewHolder>(options) {

    private lateinit var onFoodOrderOnClickListener: FoodOrderOnClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodOrderViewHolder {
        return FoodOrderViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.food_order_layout, parent, false)
        )
    }

    override fun onBindViewHolder(
        holder: FoodOrderViewHolder,
        position: Int,
        model: FoodOrder
    ) {

        holder.id.text = model.getId()
        holder.timestamp.text = Utility.formatMillisecondsIntoDate(model.getTimestamp())

        if (model.isPaid()) {
            // also change bg
            holder.cost.text = String.format(
                "%s : %s BDT", "Paid", model.getCost()
            )
        } else {
            // also change bg
            holder.cost.text = String.format(
                "%s : %s BDT", "Due", model.getCost()
            )
        }

        holder.openItems.setOnClickListener(View.OnClickListener {
            onFoodOrderOnClickListener.onClick(model)
        })

    }

    class FoodOrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val id: TextView = itemView.findViewById(R.id.id)
        val openItems: TextView = itemView.findViewById(R.id.open_items)
        val timestamp: TextView = itemView.findViewById(R.id.timestamp)
        val cost: TextView = itemView.findViewById(R.id.total_cost)
    }

    fun setOnClickListener(foodOrderOnClickListener: FoodOrderOnClickListener) {
        this.onFoodOrderOnClickListener = foodOrderOnClickListener
    }
}