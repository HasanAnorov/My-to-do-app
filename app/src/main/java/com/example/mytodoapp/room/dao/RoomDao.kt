package com.example.mytodoapp.room.dao

import androidx.room.*
import com.example.mytodoapp.room.RoomUser

@Dao
interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(roomUser: RoomUser)

    @Query("select * from roomuser")
    fun getAllNotes():List<RoomUser>

    @Delete
    fun delete(roomUser: RoomUser)

    fun update()
}