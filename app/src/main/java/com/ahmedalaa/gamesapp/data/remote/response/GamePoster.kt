package com.ahmedalaa.gamesapp.data.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GamePoster(
    @Json(name = "added")
    val added: Int?,
    @Json(name = "added_by_status")
    val addedByStatus: AddedByStatus?,
    @Json(name = "background_image")
    val backgroundImage: String?,
    @Json(name = "clip")
    val clip: Clip?,
    @Json(name = "dominant_color")
    val dominantColor: String?,
    @Json(name = "genres")
    val genres: List<Genre>?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "metacritic")
    val metacritic: Int?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "playtime")
    val playtime: Int?,
    @Json(name = "rating")
    val rating: Double?,
    @Json(name = "rating_top")
    val ratingTop: Int?,
    @Json(name = "ratings")
    val ratings: List<Rating>?,
    @Json(name = "ratings_count")
    val ratingsCount: Int?,
    @Json(name = "released")
    val released: String?,
    @Json(name = "reviews_count")
    val reviewsCount: Int?,
    @Json(name = "reviews_text_count")
    val reviewsTextCount: Int?,
    @Json(name = "saturated_color")
    val saturatedColor: String?,
    @Json(name = "score")
    val score: Any?,
    @Json(name = "short_screenshots")
    val shortScreenshots: List<ShortScreenshot>?,
    @Json(name = "slug")
    val slug: String?,
    @Json(name = "suggestions_count")
    val suggestionsCount: Int?,
    @Json(name = "user_game")
    val userGame: Any?
)