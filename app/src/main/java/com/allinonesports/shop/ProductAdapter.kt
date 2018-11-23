package com.allinonesports.shop

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.allinonesports.R
import kotlinx.android.synthetic.main.product_list_item.view.*

class ProductAdapter(var products: List<Product>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_list_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ProductViewHolder).onBind(products[position])
    }

    fun setData(products: List<Product>) {
        this.products = products
        notifyDataSetChanged()
    }

    class ProductViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(product: Product) {
            view.product_name.text = product.productName
            view.product_image.setImageResource(product.image)
        }
    }
}