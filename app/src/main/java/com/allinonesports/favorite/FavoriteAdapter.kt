package com.allinonesports.favorite

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.allinonesports.R
import com.allinonesports.database.StoreDatabase
import com.allinonesports.shop.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cart_item.view.*
import org.jetbrains.anko.doAsync

class FavoriteAdapter(var context: Context, var favoriteItems: ArrayList<Favorite>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), FavoriteListener {
    override fun loadFavorite(favoriteItems: ArrayList<Favorite>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun reloadFavorite(position: Int) {
        favoriteItems.remove(favoriteItems[position])
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount() = favoriteItems.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ProductViewHolder).onBind(context, favoriteItems[position], this, position)
    }

    fun setData(favoriteItems: ArrayList<Favorite>) {
        this.favoriteItems = favoriteItems
        notifyDataSetChanged()
    }

    class ProductViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(context: Context, favorite: Favorite, favoriteListener: FavoriteListener, position: Int) {
            view.product_name.text = favorite.name

            val url = "https://api.predic8.de/" + favorite.product_url + "/photo"
            if (!favorite.product_url.isEmpty()) {
                Picasso.get().load(url).into(view.product_image)
            } else {
                view.product_image.setImageBitmap(getImage(favorite.local_url))
            }

            view.button.setOnClickListener {
                doAsync {
                    val favoriteDao = StoreDatabase.getInstance(context.applicationContext).getFavoriteItems()
                    favoriteDao.delete(favorite)

                    val productDao = StoreDatabase.getInstance(context.applicationContext).getProducts()
                    val product = Product(favorite.name, favorite.price, favorite.product_url, favorite.local_url, favorite.isCart, false, id = favorite.id)
                    productDao.update(product)
                }
                Toast.makeText(context, "Product removed successfully from favorite", Toast.LENGTH_SHORT).show()
                favoriteListener.reloadFavorite(position)
            }
        }

        private fun getImage(image: ByteArray?): Bitmap {
            return BitmapFactory.decodeByteArray(image, 0, image!!.size)
        }
    }

}
