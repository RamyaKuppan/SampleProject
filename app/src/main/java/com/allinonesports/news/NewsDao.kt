package com.allinonesports.news

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface NewsDao {
    @Insert
    fun insert(news: News)

    @Insert
    fun insertAll(news: ArrayList<News>)

    @Query("Select * from news_table")
    fun getNews(): List<News>

}