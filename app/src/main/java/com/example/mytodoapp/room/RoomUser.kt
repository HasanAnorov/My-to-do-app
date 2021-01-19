package com.example.mytodoapp.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mytodoapp.model.Priority

@Entity
class RoomUser (
    @PrimaryKey(autoGenerate = true)
    var id:Int? ,
    var title: String?,
    var description:String?,
    var deadline:String?,
    var priority: Priority
)