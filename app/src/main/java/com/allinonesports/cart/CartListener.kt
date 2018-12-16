package com.allinonesports.cart

interface CartListener {
    fun loadCart(carts: ArrayList<Cart>)

    fun reloadCart(position: Int)
}