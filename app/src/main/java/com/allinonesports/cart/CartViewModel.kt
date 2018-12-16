package com.allinonesports.cart

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import org.jetbrains.anko.doAsync

class CartViewModel(application: Application, private var cartDao: CartDao) : AndroidViewModel(application) {

    private lateinit var cartListener: CartListener

    fun getProducts(cartListener: CartListener) {
        this.cartListener = cartListener
        var products = ArrayList<Cart>()
        doAsync {
            products = cartDao.getProducts() as ArrayList<Cart>
            cartListener.loadCart(products)
        }
    }

    class Factory(private val application: Application, private val cartDao: CartDao) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(Application::class.java, CartDao::class.java).newInstance(application, cartDao)
        }
    }
}