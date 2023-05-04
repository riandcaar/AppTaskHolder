package com.example.apptasknew

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import androidx.room.Room
import com.example.apptasknew.data.database.MyDatabase

class MyApp : Application() {
    init {
        instance = this
    }


    override fun onCreate() {
        super.onCreate()
    }


    companion object {
        private  var instance: MyApp? = null

        private fun applicationContext() : Context {
           return  requireNotNull(instance).applicationContext
        }

        private val room by lazy {
            Room.databaseBuilder(
                applicationContext(),
                MyDatabase::class.java,
                "database"
            ).fallbackToDestructiveMigration().build()
        }

        fun getDatabase() = room

    }
}
