package com.allinonesports.headlines

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.allinonesports.R
import kotlinx.android.synthetic.main.headline_list_item.view.*

class HeadlineAdapter(var headlines: List<HeadlinesItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.headline_list_item, parent, false)
        return HeadlineViewHolder(view)
    }

    override fun getItemCount() = headlines.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HeadlineViewHolder).onBind(headlines[position])
    }

    fun setData(headlinesList: List<HeadlinesItem>) {
        headlines = headlinesList
        notifyDataSetChanged()
    }

    class HeadlineViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(headlinesItem: HeadlinesItem) {
            view.image.setImageResource(R.drawable.ic_launcher_background)
            view.title.text = headlinesItem.heading
            view.subtitle.text = headlinesItem.subtitle
            view.info.text = headlinesItem.info
        }
    }
}