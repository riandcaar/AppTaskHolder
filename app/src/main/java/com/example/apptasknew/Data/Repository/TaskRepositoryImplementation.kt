package com.example.apptasknew.Data.Repository

import androidx.lifecycle.LiveData
import com.example.apptasknew.Data.Daos.TaskDao
import com.example.apptasknew.Data.Entity.TaskEntity

class TaskRepositoryImplementation(private val taskDao: TaskDao) : InterfaceRepository {
    override val getAllData: LiveData<List<TaskEntity>> = taskDao.getAllData()
    override fun insertData(taskEntity: TaskEntity) {
        taskDao.insertData(taskEntity)
    }
}