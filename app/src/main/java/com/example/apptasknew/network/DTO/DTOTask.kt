package com.example.apptasknew.network.DTO

import kotlinx.serialization.Serializable

@Serializable
data class DTOTask (
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)
