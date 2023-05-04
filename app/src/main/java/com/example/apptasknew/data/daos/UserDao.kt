package com.example.apptasknew.data.daos

import androidx.room.*
import com.example.apptasknew.data.entity.TaskEntity
import com.example.apptasknew.data.entity.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun createUser(user:UserEntity):Long?

    @Query("SELECT * FROM user_table WHERE userName = :userName AND userPassword = :userPassword")
    suspend fun getUser(userName: String, userPassword: String): UserEntity?

    @Query("UPDATE user_table SET userPassword = :newPassword WHERE userName = :userName")
    suspend fun modifyPassword(userName: String, newPassword: String): Int

    @Query("UPDATE user_table SET userName = :newUserName WHERE userId = :userId")
    suspend fun modifyUserName(userId: Long, newUserName: String): Int

    @Query("DELETE FROM user_Table WHERE userName = :userName AND userPassword = :userPassword")
    suspend fun deleteUser(userName: String, userPassword: String): Int

    @Query("SELECT * FROM user_table WHERE userId = :userId")
    suspend fun getUserById(userId: Long): UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUser(user:UserEntity)

    @Query("SELECT * FROM user_table WHERE taskList = :taskList")
    suspend fun getTaskList(taskList: MutableList<TaskEntity>): UserEntity?

}
