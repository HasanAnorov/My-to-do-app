package com.example.mytodoapp.model

import java.io.Serializable

class Note :Serializable{
    var id:Int = 0
    var title:String = ""
    var description :String = ""
    var time :String = ""
    var priority:Priority = Priority.None


    constructor()

    constructor(title: String, description: String, time: String, priority: Priority) {
        this.title = title
        this.description = description
        this.time = time
        this.priority = priority
    }

    constructor(id: Int, title: String, description: String, time: String, priority: Priority) {
        this.id = id
        this.title = title
        this.description = description
        this.time = time
        this.priority = priority
    }


}