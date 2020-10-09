package com.ahmedalaa.recipes.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ahmedalaa.recipes.data.model.Recipe

@Database(entities = [Recipe::class], version = 1)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}