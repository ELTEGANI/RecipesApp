package com.ahmedalaa.gamesapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ahmedalaa.gamesapp.data.remote.response.AddedByStatus
import com.ahmedalaa.gamesapp.data.remote.response.Clip
import com.ahmedalaa.gamesapp.data.remote.response.Genre
import com.ahmedalaa.gamesapp.data.remote.response.Rating

@Entity(tableName = "game_poster")
data class GamePosterDao(
    val added: Int?,
    val addedByStatus: AddedByStatus?,
    val backgroundImage: String?,
    val clip: Clip?,
    val dominantColor: String?,
    val genres: List<Genre>?,
    @PrimaryKey
    val id: Int?,
    val metacritic: Int?,
    val name: String?,
    val playtime: Int?,
    val rating: Double?,
    val ratingTop: Int?,
    val ratings: List<Rating>?,
    val ratingsCount: Int?,
    val released: String?,
    val reviewsCount: Int?,
    val reviewsTextCount: Int?,
    val saturatedColor: String?,
    val score: Any?,
    val shortScreenshots: List<String>,
    val slug: String?,
    val suggestionsCount: Int?,
    val userGame: Any?
)