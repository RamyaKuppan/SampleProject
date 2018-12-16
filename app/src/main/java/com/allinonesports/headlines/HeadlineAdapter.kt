package com.allinonesports.headlines

import android.content.Context
import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.allinonesports.database.StoreDatabase
import com.allinonesports.news.News
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.headline_list_item.view.*
import org.jetbrains.anko.doAsync


class HeadlineAdapter(var context: Context, var headlines: List<HeadlinesItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.headline_list_item, parent, false)
        return HeadlineViewHolder(view)
    }

    override fun getItemCount() = headlines.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HeadlineViewHolder).onBind(context, headlines[position])
    }

    fun setData(headlinesList: List<HeadlinesItem>) {
        headlines = headlinesList
        notifyDataSetChanged()
    }

    class HeadlineViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(context: Context, headlinesItem: HeadlinesItem) {
            view.title.text = headlinesItem.heading
            view.subtitle.text = headlinesItem.subtitle
            view.info.text = headlinesItem.info
            view.author.text = headlinesItem.author
            view.published.text = headlinesItem.publishedAt
            view.read_later.paintFlags = view.read_later.paintFlags or Paint.UNDERLINE_TEXT_FLAG
            if (!headlinesItem.urlToImage.isNullOrEmpty())
                Picasso.get().load(headlinesItem.urlToImage).into(view.image)

            view.read_later.setOnClickListener({
                if (!headlinesItem.readLater) {
                    doAsync {
                        val productDao = StoreDatabase.getInstance(context.applicationContext).getHeadings()
                        headlinesItem.readLater = true
                        productDao.update(headlinesItem)

                        val newsDao = StoreDatabase.getInstance(context.applicationContext).getNews()
                        val news = News(headlinesItem.heading, headlinesItem.subtitle, headlinesItem.info,
                                headlinesItem.author, headlinesItem.urlToImage, headlinesItem.publishedAt)
                        newsDao.insert(news)
                    }
                    Toast.makeText(context, "You can read this news later in News section", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}