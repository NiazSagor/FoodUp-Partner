package com.duodevloopers.fooduppartner.model

class Room(
    public var room: String = "",
    public var teacher: String = "",
    public var teacherPhone: String = "",
    public var status: Boolean = false
) {


    constructor() : this("", "", "", false)

}