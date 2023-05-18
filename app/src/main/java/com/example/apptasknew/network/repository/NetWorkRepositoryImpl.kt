package com.example.apptasknew.network.repository

import com.example.apptasknew.network.dtoRepository.TaskDto
import com.example.apptasknew.network.TaskApi
import com.example.apptasknew.network.dtoRepository.CreatePostRequest
import com.example.apptasknew.network.dtoRepository.PostResponse

class NetWorkRepositoryImpl : NetworkRepositoryInterface {
    private val service = TaskApi.retrofitService

    override suspend fun getTaskFromNetWork(): Result<List<TaskDto>> {
        val response = service.getTask()

        return if (response.isSuccessful) {
            Result.success(response.body()!!)
        } else {
            val error = Error(response.errorBody().toString())
            Result.failure(error)
        }
    }

    override suspend fun getTaskByID(userId: Int): Result<List<TaskDto>> {
        return try {
        val response = service.getTaskByID(userId)
        Result.success(response.body()!!)
    } catch (error: Exception){
        Result.failure(error)
    }
    }

    override suspend fun createPost(
        title: String,
        body: String,
        userId: Int
    ): Result<PostResponse> {
        return try {
            val postRequest = CreatePostRequest(title, body, userId)
            val response = TaskApi.retrofitService.createPost(postRequest)
            if (response.isSuccessful) {
                val postResponse = response.body()
                Result.success(postResponse!!)
            } else {
                Result.failure(Exception("Failed to create post"))
            }
        } catch (error: Exception) {
            Result.failure(error)
        }
    }
}
