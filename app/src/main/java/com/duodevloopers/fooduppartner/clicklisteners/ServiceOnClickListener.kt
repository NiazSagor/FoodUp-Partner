package com.duodevloopers.fooduppartner.clicklisteners

import com.duodevloopers.fooduppartner.model.ServiceOrder

interface ServiceOnClickListener {
    fun onOpenLink(link: String)
    fun onMarkDone(model: ServiceOrder)
}