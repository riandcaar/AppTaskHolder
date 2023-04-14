package com.example.apptasknew.Data.Daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.apptasknew.Data.Entity.TaskEntity

@Dao
interface TaskDao {

    @Query("SELECT * FROM task_table WHERE title LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): LiveData<List<TaskEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertData (taskEntity: TaskEntity)

    @Query("SELECT * FROM task_table")
    fun getAllData(): LiveData<List<TaskEntity>>

}