package com.allinonesports.shop

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.allinonesports.R
import com.allinonesports.cart.Cart
import com.allinonesports.database.StoreDatabase
import com.allinonesports.favorite.Favorite
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_list_item.view.*
import org.jetbrains.anko.doAsync

class ProductAdapter(var context: Context, var products: List<Product>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_list_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ProductViewHolder).onBind(context, products[position])
    }

    fun setData(products: List<Product>) {
        this.products = products
        notifyDataSetChanged()
    }

    class ProductViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(context: Context, product: Product) {
            view.product_name.text = product.name

            val url = "https://api.predic8.de/" + product.product_url + "/photo"
            if (!product.product_url.isEmpty()) {
                Picasso.get().load(url).into(view.product_image)
            } else {
                view.product_image.setImageBitmap(getImage(product.local_url))
            }

            view.cart.setOnClickListener {

                if (!product.isCart) {
                    doAsync {
                        val productDao = StoreDatabase.getInstance(context.applicationContext).getProducts()
                        product.isCart = true
                        productDao.update(product)

                        val cartDao = StoreDatabase.getInstance(context.applicationContext).getCartItems()
                        val cart = Cart(product.name, product.price, product.product_url, product.local_url, product.isFavorite, product.id)
                        cartDao.insert(cart)
                    }
                    Toast.makeText(context, "Product added successfully to cart", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Product added already to cart", Toast.LENGTH_SHORT).show()
                }
            }

            view.favorite.setOnClickListener {
                if (!product.isFavorite) {
                    doAsync {
                        val productDao = StoreDatabase.getInstance(context.applicationContext).getProducts()
                        product.isFavorite = true
                        productDao.update(product)
                        val favoriteDao = StoreDatabase.getInstance(context.applicationContext).getFavoriteItems()
                        val favorite = Favorite(product.name, product.price, product.product_url, product.local_url, product.isCart, product.id)
                        favoriteDao.insert(favorite)
                    }
                    Toast.makeText(context, "Product added successfully to favorite", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Product added already to favorite", Toast.LENGTH_SHORT).show()
                }
            }

            view.share.setOnClickListener {
                val sharingIntent = Intent(Intent.ACTION_SEND)
                sharingIntent.type = "text/plain"
                val productName = "Product name ${product.name}"
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, productName)
                context.startActivity(Intent.createChooser(sharingIntent, "Share using"))
            }
        }

        private fun getImage(image: ByteArray?): Bitmap {
            return BitmapFactory.decodeByteArray(image, 0, image!!.size)
        }
    }


}