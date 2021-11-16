package com.duodevloopers.fooduppartner.model

class FoodOrder(
    private var building: String,
    private var cost: String,
    private var done: Boolean,
    private var id: String,
    private var item: String,
    private var paid: Boolean,
    private var timestamp: String
) {

    constructor() : this("", "", false, "", "", false, "")

    fun getBuilding(): String {
        return building
    }


    fun getItems(): String {
        return item
    }

//    fun getItems(): List<String> = item.split("#")

    fun getCost(): String {
        return cost
    }

    fun isDone(): Boolean {
        return done
    }


    fun getId(): String {
        return id
    }

    fun isPaid(): Boolean {
        return paid
    }

    fun getTimestamp(): Long {
        return timestamp.toLong()
    }
}