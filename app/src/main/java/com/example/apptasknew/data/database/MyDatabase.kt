package com.example.apptasknew.data.database

import android.content.Context
import androidx.room.*
import com.example.apptasknew.data.daos.TaskDao
import com.example.apptasknew.data.daos.UserDao
import com.example.apptasknew.data.entity.TaskEntity
import com.example.apptasknew.data.entity.UserEntity
import com.example.apptasknew.data.utils.DataConverter

@Database(entities = [TaskEntity::class, UserEntity::class], version = 1, exportSchema = false)

@TypeConverters(DataConverter::class)
abstract class MyDatabase: RoomDatabase() {

    abstract fun taskDao(): TaskDao
    abstract fun userDao(): UserDao

}