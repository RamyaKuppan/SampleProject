package com.allinonesports.headlines

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update

@Dao
interface HeadingDao {
    @Insert
    fun insert(headline: HeadlinesItem)

    @Insert
    fun insertAll(headlines: ArrayList<HeadlinesItem>)

    @Update
    fun update(headline: HeadlinesItem)

    @Query("Select * from headlines_table")
    fun getHeadings(): List<HeadlinesItem>

}