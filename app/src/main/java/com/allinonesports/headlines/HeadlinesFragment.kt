package com.allinonesports.headlines

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.allinonesports.database.StoreDatabase

class HeadlinesFragment : Fragment(), HeadlinesListener {

    lateinit var adapter: HeadlineAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_headline, container, false)
        adapter = HeadlineAdapter(context!!, ArrayList<HeadlinesItem>())
        view.headline_list.adapter = adapter
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initializeHeadlinesList()

    }

    override fun loadHeadlines(headlines: ArrayList<HeadlinesItem>) {
        adapter.setData(headlines)
    }

    /**
     * Create a viewModel object using factory pattern
     */
    private fun initializeHeadlinesList() {
        val headingDao = StoreDatabase.getInstance(activity!!.applicationContext).getHeadings()
        val productListFactory = HeadlinesViewModel.Factory(activity!!.application, headingDao)

        val productListViewModel = ViewModelProviders.of(this, productListFactory)
                .get(HeadlinesViewModel::class.java)
        productListViewModel.getHeadlines(this)
    }
}