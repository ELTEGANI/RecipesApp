package com.ahmedalaa.gamesapp.data.remote.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Rating(
    @Json(name = "count")
    val count: Int?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "percent")
    val percent: Double?,
    @Json(name = "title")
    val title: String?
)