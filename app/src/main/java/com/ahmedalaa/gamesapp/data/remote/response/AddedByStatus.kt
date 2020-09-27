package com.ahmedalaa.gamesapp.data.remote.response


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddedByStatus(
    @Json(name = "beaten")
    val beaten: Int?,
    @Json(name = "dropped")
    val dropped: Int?,
    @Json(name = "owned")
    val owned: Int?,
    @Json(name = "playing")
    val playing: Int?,
    @Json(name = "toplay")
    val toplay: Int?,
    @Json(name = "yet")
    val yet: Int?
)