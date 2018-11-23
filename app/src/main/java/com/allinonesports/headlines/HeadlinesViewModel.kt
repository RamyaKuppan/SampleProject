package com.allinonesports.headlines

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import org.jetbrains.anko.doAsync

class HeadlinesViewModel(application: Application, private var headingDao: HeadingDao) : AndroidViewModel(application) {

    private lateinit var headlinesListener: HeadlinesListener

    fun getHeadlines(headlinesListener: HeadlinesListener) {
        this.headlinesListener = headlinesListener
        getHeadlines(headingDao)
    }

    private fun getHeadlines(headingDao: HeadingDao) {
        var headlines = ArrayList<HeadlinesItem>()
        doAsync {
            headlines = headingDao.getHeadings() as ArrayList<HeadlinesItem>
            headlinesListener.loadHeadlines(headlines)
        }
    }

    class Factory(private val application: Application, private val headingDao: HeadingDao) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(Application::class.java, HeadingDao::class.java).newInstance(application, headingDao)
        }
    }
}