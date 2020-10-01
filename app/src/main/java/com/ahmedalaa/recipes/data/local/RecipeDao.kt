package com.ahmedalaa.recipes.data.local

import androidx.room.*
import com.ahmedalaa.recipes.data.model.Recipe

@Dao
interface RecipeDao {

    @Insert
    suspend fun insert(recipe: Recipe)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(recipe: List<Recipe>)

    @Delete
    suspend fun delete(recipe: Recipe)

    @Query("select * from recipe")
    suspend fun getRecipes():List<Recipe>

    @Query("DELETE FROM recipe")
    suspend fun deleteAll()
}