package com.allinonesports.cart

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface CartDao {
    @Insert
    fun insert(cart: Cart)

    @Insert
    fun insertAll(carts: ArrayList<Cart>)

    @Query("Select * from cart_table")
    fun getProducts(): List<Cart>

    @Delete
    fun delete(cart: Cart)

}