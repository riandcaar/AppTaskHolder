package com.example.apptasknew.Data.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class TaskEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var description: String
)
