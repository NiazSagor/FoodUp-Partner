package com.duodevloopers.fooduppartner

interface ServiceOnClickListener {
    fun onOpenLink(link: String)
    fun onMarkDone(model: ServiceOrder)
}