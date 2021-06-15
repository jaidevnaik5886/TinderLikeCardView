package com.shaadi.peopleinteractive.responses.matches


import com.google.gson.annotations.SerializedName

data class MatchesResponse(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<Result>
)