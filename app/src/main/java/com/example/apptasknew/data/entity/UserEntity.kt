package com.example.apptasknew.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val userId: Long = 0L,
    val userName: String="",
    var userPassword: String="",
    val taskList: MutableList<TaskEntity> = mutableListOf(),
)
