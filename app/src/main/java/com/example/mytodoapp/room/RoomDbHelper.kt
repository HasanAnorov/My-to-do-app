package com.example.mytodoapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mytodoapp.room.dao.RoomDao

@Database(entities = [RoomUser::class],version = 1)
abstract class RoomDbHelper :RoomDatabase() {

    abstract fun roomDao() : RoomDao

     object DatabaseBuilder{
        private var instance :RoomDbHelper? =null

         fun getInstance(context: Context):RoomDbHelper{
             if (instance==null){
                 synchronized(RoomDbHelper::class.java){
                     instance = buildRoom(context)
                 }
             }
             return instance!!
         }

         private fun buildRoom(context: Context) =
             Room.databaseBuilder(context.applicationContext,RoomDbHelper::class.java,"room_db.db")
                 .allowMainThreadQueries()
                 .build()

     }

}