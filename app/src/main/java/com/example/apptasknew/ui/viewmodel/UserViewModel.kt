package com.example.apptasknew.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apptasknew.data.repository.LocalRepositoryImpl
import com.example.apptasknew.data.repository.LocalRepositoryInterface
import com.example.apptasknew.ui.ErrorTags
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private val _registerResult = MutableLiveData("")
    val registerResult: LiveData<String> = _registerResult

    private val _loginResult = MutableLiveData("")
    val loginResult: LiveData<String> = _loginResult

    private val _userIdResult = MutableLiveData("")
    val userIdResult: LiveData<String> = _userIdResult


    private val localRepository: LocalRepositoryInterface = LocalRepositoryImpl()

    fun createUser(userName: String, userPassword: String) {
        viewModelScope.launch {
            val result = localRepository.createUser(userName, userPassword)
            if (result.isSuccess) {
                _registerResult.value = result.getOrNull().toString()
            } else {
                _registerResult.value = ""
                Log.d(ErrorTags.CREATE_USER_TAG, result.getOrNull().toString())
            }
        }
    }

    fun loginUser(userName: String, userPassword: String) {
        viewModelScope.launch {
            val result = localRepository.getUser(userName, userPassword)
            if (result.isSuccess) {
                _loginResult.value = result.getOrNull()?.userId.toString()
            } else {
                _loginResult.value = ""
            }

        }
    }

    fun getUserById (userId : Long){
        viewModelScope.launch {
            val result = localRepository.getUserById(userId)
            if (result.isSuccess) {
                _userIdResult.value = result.getOrNull()?.userId.toString()
            } else {
                _userIdResult.value = ""
                Log.d(ErrorTags.CREATE_USER_TAG, result.getOrNull().toString())
            }
        }
    }
}