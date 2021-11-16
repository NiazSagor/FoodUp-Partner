package com.duodevloopers.fooduppartner.model

class ServiceOrder(
    private var cost: String,
    private var done: Boolean,
    private var id: String,
    private var link: String,
    private var page: Double,
    private var paid: Boolean,
    private var timestamp: String,
    private var type: String

) {

    constructor() : this("", false, "", "", 0.00, false, "", "")

    fun getCost(): String {
        return cost
    }

    fun isDone(): Boolean {
        return done
    }

    fun getLink(): String {
        return link
    }

    fun getId(): String {
        return id
    }

    fun getPage(): Int {
        return page.toInt()
    }

    fun isPaid(): Boolean {
        return paid
    }

    fun getTimestamp(): Long {
        return timestamp.toLong()
    }

    fun getType(): String {
        return type
    }

    override fun toString(): String {
        return super.toString()
    }

}