package com.allinonesports

import android.content.Context
import android.content.SharedPreferences
import org.jetbrains.anko.defaultSharedPreferences

/**
 * Used to store the product storage details in SharedPreferences
 */
object LoginInfo {
    private const val isLogged: String = "isLogin"
    private const val username: String = "UserName"

    lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.defaultSharedPreferences
    }

    fun storeLogin(isLogin: Boolean) {
        sharedPreferences.edit().putBoolean(isLogged, isLogin).apply()
    }

    fun isLogin(): Boolean {
        return sharedPreferences.getBoolean(isLogged, false)
    }

    fun storeUserName(userName: String) {
        sharedPreferences.edit().putString(userName, userName).apply()
    }

    fun getUserName(): String {
        return sharedPreferences.getString(username, "")
    }

}