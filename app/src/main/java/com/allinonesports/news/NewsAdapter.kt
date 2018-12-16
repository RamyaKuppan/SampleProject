package com.allinonesports.news

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_item.view.*

class NewsAdapter(var news: List<News>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return HeadlineViewHolder(view)
    }

    override fun getItemCount() = news.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HeadlineViewHolder).onBind(news[position])
    }

    fun setData(news: List<News>) {
        this.news = news
        notifyDataSetChanged()
    }

    class HeadlineViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(news: News) {
            view.title.text = news.heading
            view.subtitle.text = news.subtitle
            view.info.text = news.info
            view.author.text = news.author
            view.published.text = news.publishedAt
            if (!news.urlToImage.isNullOrEmpty())
                Picasso.get().load(news.urlToImage).into(view.image)

        }
    }
}