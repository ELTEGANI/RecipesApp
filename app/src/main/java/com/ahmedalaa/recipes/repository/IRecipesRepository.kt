package com.ahmedalaa.recipes.repository

import com.ahmedalaa.recipes.data.model.Recipe
import com.ahmedalaa.recipes.utils.ApiResponse
import kotlinx.coroutines.flow.Flow

interface IRecipesRepository {

     fun getRecipes(): Flow<ApiResponse<List<Recipe>>>
}