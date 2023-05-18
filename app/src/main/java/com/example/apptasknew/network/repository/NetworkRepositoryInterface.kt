package com.example.apptasknew.network.repository

import com.example.apptasknew.network.DTO.DTOTask

interface NetworkRepositoryInterface {

    suspend fun getTaskFromNetWork(): Result<List<DTOTask>>

    suspend fun getTaskByID(userId: Int): Result<List<DTOTask>>

}