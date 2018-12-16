package com.allinonesports.news

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.allinonesports.database.StoreDatabase

class NewsFragment : Fragment(), NewsListener {

    lateinit var adapter: NewsAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_news, container, false)
        adapter = NewsAdapter(ArrayList<News>())
        view.news_list.adapter = adapter
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initializeHeadlinesList()

    }

    override fun loadNews(news: ArrayList<News>) {
        adapter.setData(news)
    }

    /**
     * Create a viewModel object using factory pattern
     */
    private fun initializeHeadlinesList() {
        val newsDao = StoreDatabase.getInstance(activity!!.applicationContext).getNews()
        val factory = NewsViewModel.Factory(activity!!.application, newsDao)

        val newsViewModel = ViewModelProviders.of(this, factory)
                .get(NewsViewModel::class.java)
        newsViewModel.getNews(this)
    }
}