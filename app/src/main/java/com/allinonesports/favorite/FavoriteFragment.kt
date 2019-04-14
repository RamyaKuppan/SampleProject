package com.allinonesports.favorite

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.allinonesports.R
import com.allinonesports.database.StoreDatabase
import kotlinx.android.synthetic.main.fragment_cart.view.*

class FavoriteFragment : Fragment(), FavoriteListener {

    lateinit var adapter: FavoriteAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_cart, container, false)
        view.cart_title.text = "Favorite"
        adapter = FavoriteAdapter(context!!, ArrayList<Favorite>())
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
        val favoriteDao = StoreDatabase.getInstance(activity!!.applicationContext).getFavoriteItems()
        val factory = FavoriteViewModel.Factory(activity!!.application, favoriteDao)

        val favoriteViewModel = ViewModelProviders.of(this, factory)
                .get(FavoriteViewModel::class.java)
        favoriteViewModel.getProducts(this)
    }

    override fun loadFavorite(favoriteItems: ArrayList<Favorite>) {
        adapter.setData(favoriteItems)
    }

    override fun reloadFavorite(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}