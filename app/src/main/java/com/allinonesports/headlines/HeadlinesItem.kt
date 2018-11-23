package com.allinonesports.headlines

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "headlines_table")
class HeadlinesItem(@ColumnInfo(name = "heading")
                    var heading: String = "",

                    @ColumnInfo(name = "subtitle")
                    var subtitle: String = "",

                    @ColumnInfo(name = "info")
                    var info: String = "",

                    @PrimaryKey(autoGenerate = true)
                    @ColumnInfo(name = "id")
                    var id: Int = 0)