package com.allinonesports.headlines

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface HeadingDao {
    @Insert
    fun insert(headline: HeadlinesItem)

    @Insert
    fun insertAll(headlines: ArrayList<HeadlinesItem>)

    @Query("Select * from headlines_table")
    fun getHeadings(): List<HeadlinesItem>

}