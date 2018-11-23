package com.allinonesports

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.allinonesports.home.HomeActivity

class SplashActivity : AppCompatActivity() {

    private val splashScreenDuration = 3000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        navigateToNextScreen()
    }

    private fun navigateToNextScreen() {
        Handler().postDelayed({
            if (LoginInfo.isLogin()) {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this, LaunchActivity::class.java)
                startActivity(intent)
            }
        }, splashScreenDuration)
    }
}
