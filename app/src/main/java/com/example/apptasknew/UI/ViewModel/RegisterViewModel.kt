package com.example.apptasknew.UI.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.apptasknew.Data.Database.TaskDatabase
import com.example.apptasknew.Data.Entity.TaskEntity
import com.example.apptasknew.Data.Repository.InterfaceRepository
import com.example.apptasknew.Data.Repository.TaskRepositoryImplementation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel (application: Application): AndroidViewModel(application){
    private val TaskDao = TaskDatabase.getDatabase(application).taskDao()
    private val repository: InterfaceRepository = TaskRepositoryImplementation(TaskDao)

    val getAllData: LiveData<List<TaskEntity>> = repository.getAllData

    fun insertData(taskEntity: TaskEntity){
        viewModelScope.launch(Dispatchers.IO){
            repository.insertData(taskEntity)
        }
    }


}