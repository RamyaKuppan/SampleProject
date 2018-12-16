package com.allinonesports.shop

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "product_table")
class Product(@ColumnInfo(name = "productname")
              var name: String = "",

              @ColumnInfo(name = "price")
              var price: Float = 0.0f,

              @ColumnInfo(name = "image")
              var product_url: String = "",

              @ColumnInfo(name = "imageUrl")
              var local_url: ByteArray? = null,

              @ColumnInfo(name = "isCart")
              var isCart: Boolean = false,

              @ColumnInfo(name = "isFavorite")
              var isFavorite: Boolean = false,

              @PrimaryKey(autoGenerate = true)
              @ColumnInfo(name = "id")
              var id: Int = 0)