package com.allinonesports.events

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import org.jetbrains.anko.doAsync

class EventsViewModel(application: Application, private var eventDao: EventDao) : AndroidViewModel(application) {

    private lateinit var eventListener: EventListener

    fun getEvents(eventListener: EventListener) {
        this.eventListener = eventListener
        var headlines = ArrayList<Event>()
        doAsync {
            headlines = eventDao.getEvents() as ArrayList<Event>
            eventListener.loadEvents(headlines)
        }
    }

    class Factory(private val application: Application, private val eventDao: EventDao) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(Application::class.java, EventDao::class.java).newInstance(application, eventDao)
        }
    }
}