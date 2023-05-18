package com.example.apptasknew.network.repository

import com.example.apptasknew.network.dtoRepository.PostResponse
import com.example.apptasknew.network.dtoRepository.TaskDto

interface NetworkRepositoryInterface {

    suspend fun getTaskFromNetWork(): Result<List<TaskDto>>

    suspend fun getTaskByID(userId: Int): Result<List<TaskDto>>

    suspend fun createPost(title: String, body: String, userId: Int): Result<PostResponse>

}