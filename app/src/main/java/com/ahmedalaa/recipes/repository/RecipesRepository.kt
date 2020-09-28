package com.ahmedalaa.recipes.repository

import androidx.room.RoomDatabase
import com.ahmedalaa.recipes.data.local.RecipeDatabase
import com.ahmedalaa.recipes.data.remote.RecipeApi
import com.ahmedalaa.recipes.model.Recipe
import com.ahmedalaa.recipes.utils.ApiResponse
import com.ahmedalaa.recipes.utils.extenstion.toRecipesEntityList
import com.ahmedalaa.recipes.utils.extenstion.toRecipesList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RecipesRepository @Inject constructor(
    val recipeDatabase: RecipeDatabase,
    val recipeApi: RecipeApi
) : IRecipesRepository {
    override suspend fun getRecipes(): Flow<ApiResponse<List<Recipe>>> {
        return flow {
            emit(ApiResponse.InProgress)
            try {
                val result = recipeApi.fetchRecipeList();
                if (result.isSuccessful) {
                    val recipes = result.body()
                    if (recipes != null) {
                        recipeDatabase.recipeDao().insertAll(recipes.toRecipesEntityList())
                        emit(ApiResponse.Success(recipes.toRecipesList()))
                    }
                    else{

                    }
                }

            } catch (e: Exception) {

            }
        }
    }
}