package com.example.apptasknew.Data.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.apptasknew.Data.Daos.TaskDao
import com.example.apptasknew.Data.Entity.TaskEntity

@Database(entities = [TaskEntity::class], version = 1, exportSchema = false)

abstract class TaskDatabase: RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {

        @Volatile
        private var INSTANCE: TaskDatabase? = null
        fun getDatabase(context: Context): TaskDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    "task_datbase"
                ).build()
                INSTANCE = instance
                return instance
            }

        }

    }
}