package com.allinonesports.favorite

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface FavoriteDao {
    @Insert
    fun insert(favoriteItem: Favorite)

    @Insert
    fun insertAll(favoriteItems: ArrayList<Favorite>)

    @Query("Select * from favorite_table")
    fun getProducts(): List<Favorite>

    @Delete
    fun delete(favorite: Favorite)

}