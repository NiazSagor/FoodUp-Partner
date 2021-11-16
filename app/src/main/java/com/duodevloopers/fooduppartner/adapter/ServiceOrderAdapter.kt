package com.duodevloopers.fooduppartner.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.duodevloopers.fooduppartner.R
import com.duodevloopers.fooduppartner.clicklisteners.ServiceOnClickListener
import com.duodevloopers.fooduppartner.model.ServiceOrder
import com.duodevloopers.fooduppartner.utility.Utility
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class ServiceOrderAdapter(options: FirestoreRecyclerOptions<ServiceOrder>) :
    FirestoreRecyclerAdapter<ServiceOrder, ServiceOrderAdapter.ServiceOrderViewHolder>(options) {

    private val TAG = "ServiceOrderAdapter"

    private lateinit var onServiceOnClickListener: ServiceOnClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceOrderViewHolder {
        return ServiceOrderViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.service_order_layout, parent, false)
        )
    }

    override fun onBindViewHolder(
        holder: ServiceOrderViewHolder,
        position: Int,
        model: ServiceOrder
    ) {


        holder.id.text = model.getId()
        holder.timestamp.text = Utility.formatMillisecondsIntoDate(model.getTimestamp())
        holder.page.text = String.format(
            "Page : %d", model.getPage()
        )

        holder.markDone.setOnClickListener(View.OnClickListener {
            onServiceOnClickListener.onMarkDone(model)
        })

        holder.openLink.setOnClickListener(View.OnClickListener {
            onServiceOnClickListener.onOpenLink(model.getLink())
        })

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

    }

    class ServiceOrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val id: TextView = itemView.findViewById(R.id.id)
        val timestamp: TextView = itemView.findViewById(R.id.timestamp)
        val type: TextView = itemView.findViewById(R.id.print_type)
        val cost: TextView = itemView.findViewById(R.id.total_cost)
        val page: TextView = itemView.findViewById(R.id.page_count)
        val openLink: TextView = itemView.findViewById(R.id.open_link)
        val markDone: TextView = itemView.findViewById(R.id.mark_done)

    }

    fun setOnClickListener(serviceOnClickListener: ServiceOnClickListener) {
        this.onServiceOnClickListener = serviceOnClickListener
    }
}