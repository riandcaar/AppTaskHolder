package com.example.apptasknew.network.calls

import com.example.apptasknew.network.DTO.DTOTask
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TaskApiService {
    @GET("posts")
    suspend fun getTask(): Response<List<DTOTask>>

    @GET("posts")
    suspend fun getTaskByID(@Query("userId") userId:Int): Response<List<DTOTask>>
}