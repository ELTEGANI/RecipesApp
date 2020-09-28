package com.ahmedalaa.recipes.repository

import com.ahmedalaa.recipes.model.Recipe
import com.ahmedalaa.recipes.utils.ApiResponse
import kotlinx.coroutines.flow.Flow

interface IRecipesRepository {

    suspend fun getRecipes(): Flow<ApiResponse<List<Recipe>>>
}