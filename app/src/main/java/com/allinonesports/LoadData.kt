package com.allinonesports

import android.util.Log
import com.allinonesports.headlines.HeadlinesItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoadData {

    fun getHeadings(): ArrayList<HeadlinesItem> {
        val headings = ArrayList<HeadlinesItem>()
        headings.add(HeadlinesItem("Add Title", "SubTitle", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."))
        headings.add(HeadlinesItem("Add Title", "SubTitle", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."))
        headings.add(HeadlinesItem("Add Title", "SubTitle", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."))
        headings.add(HeadlinesItem("Add Title", "SubTitle", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."))

        return headings
    }

    /*fun getEvents(): ArrayList<Event> {
        val events = ArrayList<Event>()
        events.add(Event("Subtitle", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,when an unknown printer took a galley of type and scrambled it to make a type specimen book.", R.drawable.ic_launcher_background))
        events.add(Event("Subtitle", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,when an unknown printer took a galley of type and scrambled it to make a type specimen book.", R.drawable.ic_launcher_background))
        events.add(Event("Subtitle", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,when an unknown printer took a galley of type and scrambled it to make a type specimen book.", R.drawable.ic_launcher_background))
        events.add(Event("Subtitle", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,when an unknown printer took a galley of type and scrambled it to make a type specimen book.", R.drawable.ic_launcher_background))
        return events
    }
*/
    /* fun getProducts(): ArrayList<Product> {
         val products = ArrayList<Product>()
         products.add(Product("Best Airlines", 12.33f, R.drawable.ic_launcher_background))
         products.add(Product("Best Airlines", 12.33f, R.drawable.ic_launcher_background))
         products.add(Product("Best Airlines", 12.33f, R.drawable.ic_launcher_background))
         products.add(Product("Best Airlines", 12.33f, R.drawable.ic_launcher_background))
         products.add(Product("Best Airlines", 12.33f, R.drawable.ic_launcher_background))
         products.add(Product("Best Airlines", 12.33f, R.drawable.ic_launcher_background))
         products.add(Product("Best Airlines", 12.33f, R.drawable.ic_launcher_background))
         return products
     }*/

    fun getHeadingFromApi(responseListener: ResponseListener) {
        val headings = Services.service.getHeadings()
        System.out.print("inside")
        headings.enqueue(object : Callback<HeadlineResponse> {
            override fun onFailure(call: Call<HeadlineResponse>?, t: Throwable?) {
                Log.i("", "")
                System.out.print("failure")
            }

            override fun onResponse(call: Call<HeadlineResponse>?, response: Response<HeadlineResponse>?) {
                val headings = ArrayList<HeadlinesItem>()
                var headlineResponse = response?.body()
                val articles: ArrayList<Articles> = headlineResponse?.articles!!
                for (article in articles) {
                    headings.add(HeadlinesItem(article.title, article.description, article.content,
                            article.author, article.urlToImage, article.publishedAt))
                }
                responseListener.headingResponseListener(headings)
                System.out.print("success")
            }
        })


    }

    fun getEventFromAPI(responseListener: ResponseListener) {
        val events = Services.service.getEvents()

        events.enqueue(object : Callback<EventResponse> {
            override fun onFailure(call: Call<EventResponse>?, t: Throwable?) {
                Log.i("", "")
                System.out.print("failure")
            }

            override fun onResponse(call: Call<EventResponse>?, response: Response<EventResponse>?) {
                val eventResponse = response?.body()
                responseListener.eventResponseListener(eventResponse?.sources!!)
                System.out.print("success")
            }
        })
    }

    fun getProduct(responseListener: ResponseListener) {
        val products = ProductService.productService.getProduct()

        products.enqueue(object : Callback<ProductResponse> {
            override fun onFailure(call: Call<ProductResponse>?, t: Throwable?) {
                System.out.print("failure")
            }

            override fun onResponse(call: Call<ProductResponse>?, response: Response<ProductResponse>?) {
                val productResponse = response?.body()
                responseListener.productListener(productResponse?.products!!)
            }
        })

    }
}