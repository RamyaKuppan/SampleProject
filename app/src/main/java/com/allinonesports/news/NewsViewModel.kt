package com.allinonesports.news

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import org.jetbrains.anko.doAsync

class NewsViewModel(application: Application, private var newsDao: NewsDao) : AndroidViewModel(application) {

    private lateinit var newsListener: NewsListener

    fun getNews(newsListener: NewsListener) {
        this.newsListener = newsListener
        getHeadlines(newsDao)
    }

    private fun getHeadlines(newsDao: NewsDao) {
        var news = ArrayList<News>()
        doAsync {
            news = newsDao.getNews() as ArrayList<News>
            newsListener.loadNews(news)
        }
    }

    class Factory(private val application: Application, private val newsDao: NewsDao) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(Application::class.java, NewsDao::class.java).newInstance(application, newsDao)
        }
    }
}