package com.example.apptasknew.Data.Repository

import androidx.lifecycle.LiveData
import com.example.apptasknew.Data.Entity.TaskEntity

interface InterfaceRepository {

    val getAllData: LiveData<List<TaskEntity>>
    fun insertData(taskEntity: TaskEntity)
}
