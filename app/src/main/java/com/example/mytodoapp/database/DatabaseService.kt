package com.example.mytodoapp.database

import com.example.mytodoapp.model.Note

interface DatabaseService {

    fun insert(note: Note)
    fun listOfNotes():List<Note>
    //fun delete(note:Note)
    fun deleteRow(note:Note)
    fun edit(note:Note)


}