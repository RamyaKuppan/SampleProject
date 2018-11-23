package com.allinonesports.shop

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface ProductDao {
    @Insert
    fun insert(product: Product)

    @Insert
    fun insertAll(products: ArrayList<Product>)

    @Query("Select * from product_table")
    fun getProducts(): List<Product>

}