package com.allinonesports.shop

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.allinonesports.R
import com.allinonesports.database.StoreDatabase
import kotlinx.android.synthetic.main.fragment_shop.view.*

class ShopFragment : Fragment(), ShopListener {

    lateinit var productList:RecyclerView

    private lateinit var adapter: ProductAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_shop, container, false)
        productList = view.product_list
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initializeProductList()

    }

    override fun loadProducts(products: ArrayList<Product>) {
        adapter = ProductAdapter(products)
        productList.adapter = adapter
       productList.layoutManager = GridLayoutManager(context, 2)
    }

    /**
     * Create a viewModel object using factory pattern
     */
    private fun initializeProductList() {
        val productDao = StoreDatabase.getInstance(activity!!.applicationContext).getProducts()
        val shopListFactory = ShopViewModel.Factory(activity!!.application, productDao)

        val shopViewModel = ViewModelProviders.of(this, shopListFactory)
                .get(ShopViewModel::class.java)
        shopViewModel.getProducts(this)
    }
}