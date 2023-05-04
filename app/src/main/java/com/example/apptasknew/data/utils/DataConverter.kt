package com.example.apptasknew.data.utils

import androidx.room.TypeConverter
import com.example.apptasknew.data.entity.TaskEntity
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class DataConverter {
    @TypeConverter
    fun fromList(value :MutableList<TaskEntity>) = Json.encodeToString(value)


    @TypeConverter
    fun toList(value :String) = Json.decodeFromString<MutableList<TaskEntity>>(value)
}