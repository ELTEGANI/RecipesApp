package com.ahmedalaa.gamesapp.data.remote.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Clips(
    @Json(name = "full")
    val full: String?,
    @Json(name = "320")
    val x320: String?,
    @Json(name = "640")
    val x640: String?
)