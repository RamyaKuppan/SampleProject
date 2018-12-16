package com.allinonesports.cart

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "cart_table")
class Cart(@ColumnInfo(name = "productname")
           var name: String = "",

           @ColumnInfo(name = "price")
           var price: Float = 0.0f,

           @ColumnInfo(name = "image")
           var product_url: String = "",

           @ColumnInfo(name = "imageUrl")
           var local_url: ByteArray? = null,

           @ColumnInfo(name = "isFavorite")
           var isFavorite: Boolean = false,

           @PrimaryKey
           @ColumnInfo(name = "id")
           var id: Int = 0)