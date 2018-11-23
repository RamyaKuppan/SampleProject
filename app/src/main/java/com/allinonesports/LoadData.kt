package com.allinonesports

import com.allinonesports.events.Event
import com.allinonesports.headlines.HeadlinesItem
import com.allinonesports.shop.Product

class LoadData {

    fun getHeadings(): ArrayList<HeadlinesItem> {
        val headings = ArrayList<HeadlinesItem>()
        headings.add(HeadlinesItem("Add Title", "SubTitle", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."))
        headings.add(HeadlinesItem("Add Title", "SubTitle", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."))
        headings.add(HeadlinesItem("Add Title", "SubTitle", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."))
        headings.add(HeadlinesItem("Add Title", "SubTitle", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."))

        return headings
    }

    fun getEvents(): ArrayList<Event> {
        val events = ArrayList<Event>()
        events.add(Event("Subtitle", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,when an unknown printer took a galley of type and scrambled it to make a type specimen book.", R.drawable.ic_launcher_background))
        events.add(Event("Subtitle", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,when an unknown printer took a galley of type and scrambled it to make a type specimen book.", R.drawable.ic_launcher_background))
        events.add(Event("Subtitle", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,when an unknown printer took a galley of type and scrambled it to make a type specimen book.", R.drawable.ic_launcher_background))
        events.add(Event("Subtitle", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,when an unknown printer took a galley of type and scrambled it to make a type specimen book.", R.drawable.ic_launcher_background))
        return events
    }

    fun getProducts(): ArrayList<Product> {
        val products = ArrayList<Product>()
        products.add(Product("Best Airlines", 12.33f, R.drawable.ic_launcher_background))
        products.add(Product("Best Airlines", 12.33f, R.drawable.ic_launcher_background))
        products.add(Product("Best Airlines", 12.33f, R.drawable.ic_launcher_background))
        products.add(Product("Best Airlines", 12.33f, R.drawable.ic_launcher_background))
        products.add(Product("Best Airlines", 12.33f, R.drawable.ic_launcher_background))
        products.add(Product("Best Airlines", 12.33f, R.drawable.ic_launcher_background))
        products.add(Product("Best Airlines", 12.33f, R.drawable.ic_launcher_background))
        return products
    }
}