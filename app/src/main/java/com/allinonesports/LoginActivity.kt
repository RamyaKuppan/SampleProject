package com.allinonesports

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.widget.Toast
import com.allinonesports.database.StoreDatabase
import com.allinonesports.events.Event
import com.allinonesports.headlines.HeadlinesItem
import com.allinonesports.home.HomeActivity
import com.allinonesports.shop.Product
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.doAsync
import java.util.regex.Pattern


class LoginActivity : AppCompatActivity(), ResponseListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        login.setOnClickListener({

            if (TextUtils.isEmpty(username.text) && TextUtils.isEmpty(password.text)) {
                Toast.makeText(this, "Enter required field", Toast.LENGTH_SHORT).show()
            } else if (!isEmailValid(username.text.toString())) {
                Toast.makeText(this, "Please enter a valid email id", Toast.LENGTH_SHORT).show()
            } else if (password.text.length < 8) {
                Toast.makeText(this, "Password should be greater than 8 character", Toast.LENGTH_SHORT).show()

            } else {
                LoginInfo.storeLogin(true)
                LoginInfo.storeUserName(username.text.toString())
                Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show()
                loadData()
            }
        })
    }

    private fun loadData() {
        LoadData().getHeadingFromApi(this)
    }

    override fun headingResponseListener(headings: ArrayList<HeadlinesItem>) {
        val storeDatabase = StoreDatabase.getInstance(application)
        LoadData().getEventFromAPI(this)
        doAsync {
            val headingDao = storeDatabase.getHeadings()
            headingDao.insertAll(headings)
        }


    }

    override fun eventResponseListener(events: ArrayList<Event>) {
        val storeDatabase = StoreDatabase.getInstance(application)

        LoadData().getProduct(this)
        doAsync {
            val eventDao = storeDatabase.getEvents()
            eventDao.insertAll(events)
        }

    }

    override fun productListener(products: ArrayList<Product>) {
        val storeDatabase = StoreDatabase.getInstance(application)

        doAsync {
            val productDao = storeDatabase.getProducts()
            productDao.insertAll(products)
        }
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun isEmailValid(email: String?): Boolean {
        if (email != null) {
            val expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
            val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
            val matcher = pattern.matcher(email)
            return matcher.matches()
        }
        return false
    }


}
