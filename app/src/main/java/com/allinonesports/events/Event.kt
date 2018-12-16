package com.allinonesports.events

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "event_table")
class Event(@ColumnInfo(name = "subtitle")
            var name: String? = "",

            @ColumnInfo(name = "info")
            var description: String? = "",

            @ColumnInfo(name = "image")
            var url: String? = "",

            @ColumnInfo(name = "category")
            var category: String? = "",

            @ColumnInfo(name = "country")
            var country: String? = "",

            @PrimaryKey
            @ColumnInfo(name = "id")
            var id: String = "")