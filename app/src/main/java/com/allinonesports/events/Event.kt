package com.allinonesports.events

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "event_table")
class Event(@ColumnInfo(name = "subtitle")
            var subtitle: String = "",

            @ColumnInfo(name = "info")
            var info: String = "",

            @ColumnInfo(name = "image")
            var image: Int = 0,

            @PrimaryKey(autoGenerate = true)
            @ColumnInfo(name = "id")
            var id: Int = 0)