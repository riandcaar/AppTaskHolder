package com.example.apptasknew.data.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.apptasknew.data.entity.TaskEntity
import com.example.apptasknew.data.entity.UserEntity

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTask (taskEntity: TaskEntity)

    //long en el insert :Task
    @Query("SELECT * FROM task_table")
    fun getAllData(): LiveData<List<TaskEntity>>

    @Query("DELETE FROM task_table WHERE id = :id")
    suspend fun deleteTask(id: Int): Int

    @Query("DELETE FROM task_table")
    suspend fun deleteAllTask()

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun createCommunityTask(taskList: List<TaskEntity>)




}
