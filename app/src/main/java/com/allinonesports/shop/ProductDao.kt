package com.allinonesports.shop

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update

@Dao
interface ProductDao {
    @Insert
    fun insert(product: Product)

    @Insert
    fun insertAll(products: ArrayList<Product>)

    @Update
    fun update(product: Product)

    @Query("Select * from product_table")
    fun getProducts(): List<Product>

}