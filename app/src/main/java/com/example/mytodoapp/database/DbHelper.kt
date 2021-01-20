package com.example.mytodoapp.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.mytodoapp.model.Note
import com.example.mytodoapp.model.Priority
import kotlin.math.log

class DbHelper(context: Context):SQLiteOpenHelper(context,Constants.DATABASE_NAME,null,Constants.VERSION),DatabaseService {

    override fun onCreate(db: SQLiteDatabase?) {
        val query = "create table ${Constants.TABLE_NAME}(${Constants.ID} integer primary  key autoincrement , ${Constants.TITLE} text, ${Constants.DESCRIPTION} text,${Constants.DEADLINE} text, ${Constants.PRIORITY} text ) "
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    override fun insert(note: Note) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(Constants.TITLE,note.title)
        contentValues.put(Constants.DESCRIPTION,note.description)
        contentValues.put(Constants.DEADLINE,note.time)
        when(note.priority){
            Priority.Low->{
                contentValues.put(Constants.PRIORITY,"Low")
            }
            Priority.Middle->{
                contentValues.put(Constants.PRIORITY,"Middle")
            }
            Priority.Urgent->{
                contentValues.put(Constants.PRIORITY,"Urgent")
            }
        }
        db.insert(Constants.TABLE_NAME,null,contentValues)
        db.close()

    }

    override fun listOfNotes(): List<Note> {

        val list =  ArrayList<Note>()
        val db =this.writableDatabase
        val query = "select*from ${Constants.TABLE_NAME}"
        val raw = db.rawQuery(query,null)
        if (raw.moveToFirst()){
            do {
                val id = raw.getInt(0)
                val title = raw.getString(1)
                val description = raw.getString(2)
                val deadline = raw.getString(3)
                var priority = Priority.None
                when(raw.getString(4)){
                    "Low" ->{
                        priority = Priority.Low
                    }
                    "Middle" ->{
                        priority = Priority.Middle
                    }
                    "Urgent" ->{
                        priority = Priority.Urgent
                    }
                }

                var note = Note(id,title,description,deadline,priority)
                list.add(note)

            }while (raw.moveToNext())
        }
            return list
    }

//    @SuppressLint("Recycle")
//    override fun delete(note: Note) {
//        val db = this.writableDatabase
//        val query = "delete from ${Constants.TABLE_NAME} where ${Constants.TITLE} = ${note.title}"
//        db.rawQuery(query,null)
//        db?.execSQL(query)
//        db.close()
//    }

    override fun edit(note: Note,id:Int) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(Constants.TITLE,note.title)
        contentValues.put(Constants.DESCRIPTION,note.description)
        contentValues.put(Constants.DEADLINE,note.time)
        when(note.priority){
            Priority.Urgent->{
                contentValues.put(Constants.PRIORITY,"Urgent")
            }
            Priority.Middle->{
                contentValues.put(Constants.PRIORITY,"Middle")
            }
            Priority.Low->{
                contentValues.put(Constants.PRIORITY,"Low")
            }
        }

          db.update(Constants.TABLE_NAME,contentValues,"${Constants.ID} = $id",null)
//        db.update(Constants.TABLE_NAME,contentValues,"${Constants.ID} LIKE ?",
//            arrayOf(arrayOf(id).toString())
//        )

        db.close()
    }

    override fun readByTitle():List<Note> {
        var list = ArrayList<Note>()
        val query = "select *from ${Constants.PRIORITY} order by ${Constants.TITLE}"
        val db = this.writableDatabase
        val raw = db.rawQuery(query,null)

        if (raw.moveToFirst()){
            do {
                val id = raw.getInt(0)
                val title = raw.getString(1)
                val description = raw.getString(2)
                val deadline = raw.getString(3)
                var priority = Priority.None
                when(raw.getString(4)){
                    "Low" ->{
                        priority = Priority.Low
                    }
                    "Middle" ->{
                        priority = Priority.Middle
                    }
                    "Urgent" ->{
                        priority = Priority.Urgent
                    }
                }
                val note = Note(id,title,description,deadline,priority)
                list.add(note)
            }while (raw.moveToNext())
        }
        return  list
    }

//    override fun readSortedListAsc(): List<Note> {
//        val list = ArrayList<Note>()
//        val query = "select *from  ${Constants.TABLE_NAME} order by ${Constants.TITLE}"
//        val db = this.writableDatabase
//        val cursor = db.rawQuery(query, null)
//
//        if (cursor.moveToFirst()) {
//            do {
//                val title = cursor.getString(1)
//                val id = cursor.getInt(0)
//
//                val message = cursor.getString(2)
//                val date = cursor.getString(3)
//                val type = cursor.getString(4)
//
//                val note = Note(id,title,message,date,type)
//                list.add(note)
//            } while (cursor.moveToNext())
//        }
//        return list
//    }

    override fun readByPriority(note: Note) {
        TODO("Not yet implemented")
    }



    override fun deleteRow(note: Note) {
       val db = this.readableDatabase
        db.delete(Constants.TABLE_NAME,"${Constants.TITLE} LIKE ?",arrayOf(note.title))
        db.close()
    }

//    override fun update(note: Note) {
//        val db = this.writableDatabase
//
//        val contentValues = ContentValues()
//        contentValues.put(Constants.TITLE,note.title)
//        contentValues.put(Constants.DESCRIPTION,note.description)
//        contentValues.put(Constants.DEADLINE,note.time)
//        when(note.priority){
//            Priority.Urgent->{
//                contentValues.put(Constants.PRIORITY,"Urgent")
//            }
//            Priority.Middle->{
//                contentValues.put(Constants.PRIORITY,"Middle")
//            }
//            Priority.Low->{
//                contentValues.put(Constants.PRIORITY,"Low")
//            }
//        }
//
//        val selection = "${Constants.TITLE} LIKE ?"
//
//        db.update(Constants.TABLE_NAME,contentValues,selection, arrayOf(note.title))
//        db.close()
//
//    }


}