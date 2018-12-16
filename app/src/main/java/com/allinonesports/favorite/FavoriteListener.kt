package com.allinonesports.favorite


interface FavoriteListener {
    fun loadFavorite(favoriteItems: ArrayList<Favorite>)

    fun reloadFavorite(position: Int)
}