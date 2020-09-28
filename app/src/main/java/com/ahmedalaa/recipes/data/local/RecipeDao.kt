package com.ahmedalaa.recipes.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RecipeDao {

    @Insert
    suspend fun insert(recipe: RecipeEntity)
    @Insert
    suspend fun insertAll(recipe: List<RecipeEntity>)
    @Query("select * from recipe")
    suspend fun getRecipes():List<RecipeEntity>
}