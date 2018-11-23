package com.allinonesports.shop

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "product_table")
class Product(@ColumnInfo(name = "productname")
              var productName: String = "",

              @ColumnInfo(name = "price")
              var price: Float = 0.0f,

              @ColumnInfo(name = "image")
              var image: Int = 0,

              @PrimaryKey(autoGenerate = true)
              @ColumnInfo(name = "id")
              var id: Int = 0)