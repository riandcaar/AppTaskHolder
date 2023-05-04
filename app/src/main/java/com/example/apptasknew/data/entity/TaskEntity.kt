package com.example.apptasknew.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "task_table")
data class TaskEntity (
    var title: String = "",
    var description: String = "",
    @PrimaryKey(autoGenerate = true)
    val id: Long =0L,
)
