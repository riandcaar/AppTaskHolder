package com.example.apptasknew.network.dtoRepository

import kotlinx.serialization.Serializable

@Serializable
data class TaskDto (
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
    
    
)
