package com.example.apptasknew.network.repository

import com.example.apptasknew.network.DTO.DTOTask
import com.example.apptasknew.network.TaskApi
import com.example.apptasknew.network.calls.TaskApiService

class NetWorkRepositoryImpl : NetworkRepositoryInterface {
    private val service = TaskApi.retrofitService

    override suspend fun getTaskFromNetWork(): Result<List<DTOTask>> {
        val response = service.getTask()

        return if (response.isSuccessful) {
            Result.success(response.body()!!)
        } else {
            val error = Error(response.errorBody().toString())
            Result.failure(error)
        }
    }

    override suspend fun getTaskByID(userId: Int): Result<List<DTOTask>> {
        return try {
        val response = service.getTaskByID(userId)
        Result.success(response.body()!!)
    } catch (error: Exception){
        Result.failure(error)
    }
    }
}
