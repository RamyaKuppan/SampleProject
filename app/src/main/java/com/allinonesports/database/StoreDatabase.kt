package com.allinonesports.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.allinonesports.events.Event
import com.allinonesports.events.EventDao
import com.allinonesports.headlines.HeadingDao
import com.allinonesports.headlines.HeadlinesItem
import com.allinonesports.shop.Product
import com.allinonesports.shop.ProductDao

@Database(entities = [HeadlinesItem::class, Event::class, Product::class], version = 1)
abstract class StoreDatabase : RoomDatabase() {

    abstract fun getHeadings(): HeadingDao

    abstract fun getEvents(): EventDao

    abstract fun getProducts(): ProductDao

    companion object {
        private var INSTANCE: StoreDatabase? = null

        private const val DB_NAME = "retail_store_db"

        private val lock = Any()

        fun getInstance(context: Context): StoreDatabase {
            synchronized(lock) {

                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            StoreDatabase::class.java, DB_NAME)
                            .build()
                }
                return INSTANCE!!
            }
        }
    }
}