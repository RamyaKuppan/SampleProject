package com.allinonesports.events

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.allinonesports.R
import kotlinx.android.synthetic.main.event_list_item.view.*

class EventAdapter(var events: List<Event>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.event_list_item, parent, false)
        return EventViewHolder(view)
    }

    override fun getItemCount() = events.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as EventViewHolder).onBind(events[position])
    }

    fun setData(events: List<Event>) {
        this.events = events
        notifyDataSetChanged()
    }

    class EventViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(event: Event) {
            view.event_title.text = event.name
            view.event_description.text = event.description
            view.category.text = "Categoty : " + event.category
            view.country.text = "Country: " + event.country
            view.link.text = event.url

            /*if (!event.url!!.isEmpty()) {
                Picasso.get().load(event.url).into(view.event_image)
            } else {
                view.event_image.setImageResource(R.drawable.ic_launcher_background)
            }*/

        }
    }
}