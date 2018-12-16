package com.allinonesports.favorite

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import org.jetbrains.anko.doAsync

class FavoriteViewModel(application: Application, private var favoriteDao: FavoriteDao) : AndroidViewModel(application) {

    private lateinit var favoriteListener: FavoriteListener

    fun getProducts(favoriteListener: FavoriteListener) {
        this.favoriteListener = favoriteListener
        var products = ArrayList<Favorite>()
        doAsync {
            products = favoriteDao.getProducts() as ArrayList<Favorite>
            favoriteListener.loadFavorite(products)
        }
    }

    class Factory(private val application: Application, private val favoriteDao: FavoriteDao) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(Application::class.java, FavoriteDao::class.java).newInstance(application, favoriteDao)
        }
    }
}