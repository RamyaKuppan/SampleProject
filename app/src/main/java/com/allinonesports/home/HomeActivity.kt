package com.allinonesports.home

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import com.allinonesports.EmptyFragment
import com.allinonesports.R
import com.allinonesports.cart.CartFragment
import com.allinonesports.events.EventFragment
import com.allinonesports.favorite.FavoriteFragment
import com.allinonesports.headlines.HeadlinesFragment
import com.allinonesports.news.NewsFragment
import com.allinonesports.scan.ScanFragment
import com.allinonesports.shop.ShopFragment
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.content_data.*

class HomeActivity : AppCompatActivity(), View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        addFragment(HeadlinesFragment())
        headlines.setOnClickListener(this)
        events.setOnClickListener(this)
        shop.setOnClickListener(this)
        scan.setOnClickListener(this)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        toolbar.title = "AllInOneSports"
        nav_view.setNavigationItemSelectedListener(this)
    }

    /**
     * Used to add the fragment that passed in method argument
     * @param fragment Fragment to add in activity
     */
    private fun addFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_layout, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.headlines -> addFragment(HeadlinesFragment())
            R.id.events -> addFragment(EventFragment())
            R.id.shop -> addFragment(ShopFragment())
            R.id.scan -> addFragment(ScanFragment())
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_news -> {
                addFragment(NewsFragment())
            }
            R.id.nav_cart -> {
                addFragment(CartFragment())
            }
            R.id.nav_favorite -> {
                addFragment(FavoriteFragment())
            }
            R.id.nav_forum -> {
                addFragment(EmptyFragment())
            }
            R.id.nav_promo -> {
                addFragment(EmptyFragment())
            }
            R.id.nav_purchases -> {
                addFragment(EmptyFragment())
            }
            R.id.nav_scan -> {
                addFragment(EmptyFragment())
            }
            R.id.nav_updates -> {
                addFragment(EmptyFragment())
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
