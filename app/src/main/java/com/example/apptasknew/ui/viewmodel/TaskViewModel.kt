package com.example.apptasknew.ui.viewmodel

import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apptasknew.data.entity.TaskEntity
import com.example.apptasknew.data.repository.LocalRepositoryImpl
import com.example.apptasknew.data.repository.LocalRepositoryInterface
import com.example.apptasknew.network.DTO.DTOTask
import com.example.apptasknew.network.repository.NetWorkRepositoryImpl
import com.example.apptasknew.network.repository.NetworkRepositoryInterface
import com.example.apptasknew.ui.ErrorTags
import kotlinx.coroutines.launch

class TaskViewModel : ViewModel() {


    // Local

    private val _taskAddResult = MutableLiveData(emptyList<TaskEntity>())
    val taskAddResult: MutableLiveData<List<TaskEntity>> = _taskAddResult

    private val _taskListResult = MutableLiveData(emptyList<TaskEntity>())
    val taskListResult: MutableLiveData<List<TaskEntity>> = _taskListResult

    private val _listResult = MutableLiveData(emptyList<TaskEntity>())
    val listResult: MutableLiveData<List<TaskEntity>> = _listResult

    private val _communityListResult = MutableLiveData(emptyList<TaskEntity>())
    val communityListResult: MutableLiveData<List<TaskEntity>> = _communityListResult

    private val localRepository: LocalRepositoryInterface = LocalRepositoryImpl()


    // NetWork

    private val netWorkRepository: NetworkRepositoryInterface = NetWorkRepositoryImpl()

    private val _task = MutableLiveData<List<DTOTask>>()
    val task: LiveData<List<DTOTask>> = _task

    private val _listTask = MutableLiveData<List<DTOTask>>()
    val listTask: MutableLiveData<List<DTOTask>> = _listTask



    // Local
    fun insertTask(taskEntity: TaskEntity, userId: Long) {
        viewModelScope.launch {
            val result = localRepository.insertTask(taskEntity, userId)
            if (result.isSuccess) {
                _taskAddResult.value = result.getOrNull()?.taskList
            } else {
                _taskAddResult.value = emptyList()
            }
            Log.d(ErrorTags.CREATE_USER_TAG, result.getOrNull().toString())
        }
    }

    fun verifyData(title: String, description: String, userId: String): Boolean {
        return if (TextUtils.isEmpty(title) || TextUtils.isEmpty(description) || userId.isNotEmpty()) {
            true
        } else !(title.isEmpty() || description.isEmpty() || userId.isEmpty())
    }

    val getAllData: LiveData<List<TaskEntity>> = localRepository.getAllData

    fun verifyList(title: String, description: String, userId: String): Boolean {
        return if (TextUtils.isEmpty(title) || TextUtils.isEmpty(description) || userId.isNotEmpty()) {
            true
        } else !(title.isEmpty() || description.isEmpty() || userId.isEmpty())
    }

    fun createCommunityTask(taskList: List<TaskEntity>) {
        viewModelScope.launch {
            localRepository.createCommunityTask(taskList)
        }
    }

    fun getUserTask(userId: Long) {
        viewModelScope.launch {
            val myUsser = localRepository.getUserById(userId)
            if (myUsser.isSuccess) {
                _listResult.value = myUsser.getOrNull()?.taskList
            } else {
                _listResult.value = emptyList()
                Log.d(ErrorTags.CREATE_USER_TAG, myUsser.getOrNull().toString())
            }
        }

    }



    // Metodos NetWork

    fun getTaskFromNetwork() {
        viewModelScope.launch {
            val myUsser = netWorkRepository.getTaskFromNetWork()
            if (myUsser.isSuccess) {
                _task.value = myUsser.getOrNull()
            } else {
                _task.value = emptyList()
                Log.d(ErrorTags.CREATE_USER_TAG, myUsser.getOrNull().toString())
            }
        }
    }

    fun getTaskByIdNetwork(userId: Int) {
        viewModelScope.launch {
            val myUsser = netWorkRepository.getTaskByID(userId)
            if (myUsser.isSuccess) {
                _listTask.value = myUsser.getOrNull()
            } else {
                _listResult.value = emptyList()
                Log.d(ErrorTags.CREATE_USER_TAG, myUsser.getOrNull().toString())
            }
        }

    }
}

