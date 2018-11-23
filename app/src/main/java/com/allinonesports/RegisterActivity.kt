package com.allinonesports

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.widget.Toast
import com.allinonesports.database.StoreDatabase
import com.allinonesports.home.HomeActivity
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.doAsync

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        create_account.setOnClickListener({
            if (TextUtils.isEmpty(name.text) && TextUtils.isEmpty(password.text) &&
                    TextUtils.isEmpty(email.text) && TextUtils.isEmpty(country.text)) {
                Toast.makeText(this, "Enter required field", Toast.LENGTH_SHORT).show()
            } else {
                LoginInfo.storeLogin(true)
                LoginInfo.storeUserName(email.text.toString())
                Toast.makeText(this, "Created Account", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                addHeadings()
            }
        })
    }

    private fun addHeadings() {
        doAsync {
            val storeDatabase = StoreDatabase.getInstance(application)
            val headingDao = storeDatabase.getHeadings()
            headingDao.insertAll(LoadData().getHeadings())

            val eventDao = storeDatabase.getEvents()
            eventDao.insertAll(LoadData().getEvents())

            val productDao = storeDatabase.getProducts()
            productDao.insertAll(LoadData().getProducts())
        }

    }
}
