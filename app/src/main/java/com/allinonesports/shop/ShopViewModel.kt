package com.allinonesports.shop

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import org.jetbrains.anko.doAsync

class ShopViewModel(application: Application, private var productDao: ProductDao) : AndroidViewModel(application) {

    private lateinit var shopListener: ShopListener

    fun getProducts(shopListener: ShopListener) {
        this.shopListener = shopListener
        var products = ArrayList<Product>()
        doAsync {
            products = productDao.getProducts() as ArrayList<Product>
            shopListener.loadProducts(products)
        }
    }

    class Factory(private val application: Application, private val productDao: ProductDao) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(Application::class.java, ProductDao::class.java).newInstance(application, productDao)
        }
    }
}