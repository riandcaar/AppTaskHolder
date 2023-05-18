package com.example.apptasknew.network.calls

import com.example.apptasknew.network.dtoRepository.CreatePostRequest
import com.example.apptasknew.network.dtoRepository.PostResponse
import com.example.apptasknew.network.dtoRepository.TaskDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TaskApiService {
    @GET("posts")
    suspend fun getTask(): Response<List<TaskDto>>

    @GET("posts")
    suspend fun getTaskByID(@Query("userId") userId:Int): Response<List<TaskDto>>

    @POST("posts")
    suspend fun createPost(@Body post: CreatePostRequest): Response<PostResponse>
}