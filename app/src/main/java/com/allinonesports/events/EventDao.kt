package com.allinonesports.events

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface EventDao {
    @Insert
    fun insert(event: Event)

    @Insert
    fun insertAll(events: ArrayList<Event>)

    @Query("Select * from event_table")
    fun getEvents(): List<Event>
}