package com.example.apptasknew.data.repository

import androidx.lifecycle.LiveData
import com.example.apptasknew.MyApp
import com.example.apptasknew.data.daos.TaskDao
import com.example.apptasknew.data.entity.TaskEntity
import com.example.apptasknew.data.entity.UserEntity


class LocalRepositoryImpl : LocalRepositoryInterface {
    private val database = MyApp.getDatabase()
    private val userDao = database.userDao()
    private val taskDao = database.taskDao()

    override suspend fun createUser(userName: String, userPassword: String): Result<Boolean> {
        val newUser = UserEntity(
            userName = userName,
            userPassword = userPassword,
            taskList = mutableListOf()
        )
        val result = userDao.createUser(newUser)
        return try {
            val boolean = result != -1L
            Result.success(boolean)
        } catch (error: Exception) {
            Result.failure(error)
        }

    }

    override suspend fun getUser(userName: String, userPassword: String): Result<UserEntity?> {
        return try {
            val resultGetUser = userDao.getUser(userName, userPassword)
            Result.success(resultGetUser)
        } catch (error: Exception) {
            Result.failure(error)
        }
    }


    override suspend fun modifyPassword(
        userName: String,
        newPassword: String,
        userPassword: String
    ): Result<Boolean> {

        val myUser = userDao.getUser(userName, userPassword)
        return if (myUser != null) {
            try {
                val modifyIntent = userDao.modifyPassword(userName, newPassword)
                if (modifyIntent == 1)
                    Result.success(true)
                else {
                    Result.success(false)
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        } else {
            Result.failure(Error("User does not exist"))
        }
    }


    override suspend fun modifyUserName(
        userId: Long,
        userName: String,
        userPassword: String,
        newUserName: String
    ): Result<Boolean> {

        val userConfirm = userDao.getUser(userName, userPassword)
        return if (userConfirm != null) {
            try {
                val modifyIntent = userDao.modifyUserName(userId, newUserName)
                if (modifyIntent == 1)
                    Result.success(true)
                else {
                    Result.success(false)
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        } else {
            Result.failure(Error("Impossible change user"))
        }
    }

    override suspend fun getUserById(userId: Long): Result<UserEntity?> {
        return try {
            val resultGetUserById = userDao.getUserById(userId)
            Result.success(resultGetUserById)
        } catch (error: Exception) {
            Result.failure(error)
        }
    }

    override suspend fun getTaskList(taskList: MutableList<TaskEntity>): Result<UserEntity?> {
        return try {
            val resultGetList = userDao.getTaskList(taskList)
            Result.success(resultGetList)
        } catch (error: Exception) {
            Result.failure(error)
        }
    }


    override suspend fun insertTask(taskEntity: TaskEntity, userId:Long): Result<UserEntity?> {
    val myUser = userDao.getUserById(userId)
    myUser?.taskList?.add(taskEntity)
        if (myUser != null) {
            userDao.updateUser(myUser)
        }
        return Result.success(myUser)
    }


    override val getAllData: LiveData<List<TaskEntity>> = taskDao.getAllData()


    override suspend fun deleteTask(id: Int): Result<Boolean> {
        return try {
            val deleteTask = taskDao.deleteTask(id)
            if (deleteTask == 1)
                Result.success(true)
            else  Result.success(false)
        } catch (error: Exception) {
            Result.failure(error)
        }
    }

    override suspend fun createCommunityTask(taskList: List<TaskEntity>){
            taskDao.createCommunityTask(taskList)
        }
    }

