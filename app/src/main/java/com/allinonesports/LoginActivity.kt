package com.allinonesports

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.widget.Toast
import com.allinonesports.database.StoreDatabase
import com.allinonesports.home.HomeActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.doAsync

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        login.setOnClickListener({

            if (TextUtils.isEmpty(username.text) && TextUtils.isEmpty(password.text)) {
                Toast.makeText(this, "Enter required field", Toast.LENGTH_SHORT).show()
            } else {
                LoginInfo.storeLogin(true)
                LoginInfo.storeUserName(username.text.toString())
                Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                loadData()
            }
        })
    }

    private fun loadData() {
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
