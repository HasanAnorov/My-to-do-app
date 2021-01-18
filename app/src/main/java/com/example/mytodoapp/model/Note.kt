package com.example.mytodoapp.model

import android.renderscript.RenderScript

class Note {
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
}