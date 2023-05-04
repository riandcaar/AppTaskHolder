package com.example.apptasknew.data.repository

import androidx.lifecycle.LiveData
import com.example.apptasknew.data.entity.TaskEntity
import com.example.apptasknew.data.entity.UserEntity

interface LocalRepositoryInterface {

    // User
    suspend fun createUser(userName: String, userPassword: String):Result<Boolean>
    suspend fun getUser(userName: String, userPassword: String): Result<UserEntity?>
    suspend fun modifyPassword(userName: String, newPassword: String, userPassword: String): Result<Boolean>
    suspend fun modifyUserName(userId: Long, userName: String, userPassword: String, newUserName: String):Result<Boolean>
    //fun deleteUser(userName: String, userPassword: String): Result<Boolean>
    suspend fun getUserById(userId: Long): Result<UserEntity?>

    suspend fun getTaskList(taskList: MutableList<TaskEntity>): Result<UserEntity?>

    // Task
    suspend fun insertTask (taskEntity: TaskEntity, userId: Long): Result<UserEntity?>

    val getAllData: LiveData<List<TaskEntity>>

    //suspend fun getAllTasks(): Result<LiveData<List<TaskEntity>>>
    suspend fun deleteTask(id: Int): Result<Boolean>

    suspend fun createCommunityTask(taskList: List<TaskEntity>)

    //suspend fun insertTask(taskEntity: TaskEntity): Result<Boolean>



    //fun deleteAllTask(): Result<Boolean>

    // Login

}
