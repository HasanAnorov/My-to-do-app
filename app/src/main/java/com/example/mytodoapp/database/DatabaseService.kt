package com.example.mytodoapp.database

import android.content.ContentValues
import com.example.mytodoapp.model.Note

interface DatabaseService {

    fun insert(note: Note)
    fun listOfNotes():List<Note>
    //fun delete(note:Note)
    fun deleteRow(note:Note)
    fun edit(note:Note,id:Int)

    fun readByTitle():List<Note>
    fun readByPriority(note: Note)

}