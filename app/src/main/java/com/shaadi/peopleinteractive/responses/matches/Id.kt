package com.shaadi.peopleinteractive.responses.matches


import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.shaadi.peopleinteractive.database.Converters

data class Id(
    @SerializedName("name")
    val name: String,
    @TypeConverters(Converters::class)
    @SerializedName("value")
    val value: Any
)