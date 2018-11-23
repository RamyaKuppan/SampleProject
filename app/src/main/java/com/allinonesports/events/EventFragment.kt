package com.allinonesports.events

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.allinonesports.R
import com.allinonesports.database.StoreDatabase
import kotlinx.android.synthetic.main.fragment_events.view.*

class EventFragment : Fragment(), EventListener {
    lateinit var adapter: EventAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_events, container, false)
        adapter = EventAdapter(ArrayList<Event>())
        view.event_list.adapter = adapter
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initializeEventList()
    }

    /**
     * Create a viewModel object using factory pattern
     */
    private fun initializeEventList() {
        val eventDao = StoreDatabase.getInstance(activity!!.applicationContext).getEvents()
        val eventFactory = EventsViewModel.Factory(activity!!.application, eventDao)

        val eventsViewModel = ViewModelProviders.of(this, eventFactory)
                .get(EventsViewModel::class.java)
        eventsViewModel.getEvents(this)
    }

    override fun loadEvents(events: ArrayList<Event>) {
        adapter.setData(events)
    }

}