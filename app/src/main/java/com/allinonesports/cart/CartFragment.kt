package com.allinonesports.cart

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.allinonesports.database.StoreDatabase

class CartFragment : Fragment(), CartListener {
    override fun reloadCart(position: Int) {
    }

    lateinit var adapter: CartAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_cart, container, false)
        adapter = CartAdapter(context!!, ArrayList<Cart>())
        view.cart_list.adapter = adapter
        view.cart_list.layoutManager = GridLayoutManager(context, 2)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initializeCartList()
    }

    /**
     * Create a viewModel object using factory pattern
     */
    private fun initializeCartList() {
        val cartDao = StoreDatabase.getInstance(activity!!.applicationContext).getCartItems()
        val factory = CartViewModel.Factory(activity!!.application, cartDao)

        val cartViewModel = ViewModelProviders.of(this, factory)
                .get(CartViewModel::class.java)
        cartViewModel.getProducts(this)
    }

    override fun loadCart(carts: ArrayList<Cart>) {
        adapter.setData(carts)
    }


}