package com.allinonesports.cart

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.allinonesports.database.StoreDatabase
import com.allinonesports.shop.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cart_item.view.*
import org.jetbrains.anko.doAsync

class CartAdapter(var context: Context, var cartItems: ArrayList<Cart>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), CartListener {
    override fun loadCart(carts: ArrayList<Cart>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun reloadCart(position: Int) {
        cartItems.remove(cartItems[position])
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount() = cartItems.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ProductViewHolder).onBind(context, cartItems[position], this, position)
    }

    fun setData(cartItems: ArrayList<Cart>) {
        this.cartItems = cartItems
        notifyDataSetChanged()
    }

    class ProductViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(context: Context, cart: Cart, cartListener: CartListener, position: Int) {
            view.product_name.text = cart.name

            val url = "https://api.predic8.de/" + cart.product_url + "/photo"
            if (!cart.product_url.isEmpty()) {
                Picasso.get().load(url).into(view.product_image)
            } else {
                view.product_image.setImageBitmap(getImage(cart.local_url))
            }

            view.button.setOnClickListener({
                doAsync {
                    val cartDao = StoreDatabase.getInstance(context.applicationContext).getCartItems()
                    cartDao.delete(cart)

                    val productDao = StoreDatabase.getInstance(context.applicationContext).getProducts()
                    val product = Product(cart.name, cart.price, cart.product_url, cart.local_url, false, cart.isFavorite, id = cart.id)
                    productDao.update(product)
                }
                Toast.makeText(context, "Product removed successfully from cart", Toast.LENGTH_SHORT).show()
                cartListener.reloadCart(position)
            })
        }

        private fun getImage(image: ByteArray?): Bitmap {
            return BitmapFactory.decodeByteArray(image, 0, image!!.size)
        }
    }


}