package com.example.apptasknew.network

import com.example.apptasknew.network.calls.TaskApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

private const val BASE_URL =
    "https://jsonplaceholder.typicode.com/"

val contentType = "application/json".toMediaType()

val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(Json.asConverterFactory(contentType))
    .build()

object TaskApi {
    val retrofitService : TaskApiService by lazy {
        retrofit.create(TaskApiService::class.java) }
}
