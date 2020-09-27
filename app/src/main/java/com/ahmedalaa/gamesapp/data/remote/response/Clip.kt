package com.ahmedalaa.gamesapp.data.remote.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Clip(
    @Json(name = "clip")
    val clip: String?,
    @Json(name = "clips")
    val clips: Clips?,
    @Json(name = "preview")
    val preview: String?,
    @Json(name = "video")
    val video: String?
)