package com.allinonesports.news

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "news_table")
class News(@ColumnInfo(name = "heading")
           var heading: String? = "",

           @ColumnInfo(name = "subtitle")
           var subtitle: String? = "",

           @ColumnInfo(name = "info")
           var info: String? = "",

           @ColumnInfo(name = "author")
           var author: String? = "",

           @ColumnInfo(name = "imageUrl")
           var urlToImage: String? = "",

           @ColumnInfo(name = "publishedAt")
           var publishedAt: String? = "",

           @PrimaryKey(autoGenerate = true)
           @ColumnInfo(name = "id")
           var id: Int = 0)