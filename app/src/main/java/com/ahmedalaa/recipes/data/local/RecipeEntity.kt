package com.ahmedalaa.recipes.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe")
data class RecipeEntity(
    val calories: String?,
    val carbos: String?,
    val country: String?,
    val description: String?,
    val difficulty: Int?,
    val fats: String?,
    val headline: String?,
    @PrimaryKey
    val id: String?,
    val image: String?,
    val name: String?,
    val proteins: String?,
    val thumb: String?,
    val time: String?
)