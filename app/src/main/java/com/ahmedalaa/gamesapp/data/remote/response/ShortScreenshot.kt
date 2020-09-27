package com.ahmedalaa.gamesapp.data.remote.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ShortScreenshot(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "image")
    val image: String?
)