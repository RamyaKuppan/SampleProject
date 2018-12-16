package com.allinonesports

import com.allinonesports.events.Event
import com.allinonesports.headlines.HeadlinesItem
import com.allinonesports.shop.Product

interface ResponseListener {

    fun headingResponseListener(headings: ArrayList<HeadlinesItem>)

    fun eventResponseListener(events: ArrayList<Event>)

    fun productListener(products: ArrayList<Product>)
}
