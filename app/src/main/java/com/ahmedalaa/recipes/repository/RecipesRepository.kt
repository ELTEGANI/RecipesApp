package com.ahmedalaa.recipes.repository

import com.ahmedalaa.recipes.R
import com.ahmedalaa.recipes.data.local.RecipeDatabase
import com.ahmedalaa.recipes.data.model.Recipe
import com.ahmedalaa.recipes.data.remote.RecipeApi
import com.ahmedalaa.recipes.utils.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class RecipesRepository @Inject constructor(
    private val recipeDatabase: RecipeDatabase,
    private val recipeApi: RecipeApi
) : IRecipesRepository {
    override  fun getRecipes(): Flow<ApiResponse<List<Recipe>>> {
        return flow {
            emit(ApiResponse.InProgress)
            val dbRecipes = recipeDatabase.recipeDao().getRecipes()
            try {

                val result = recipeApi.fetchRecipeList()
                if (result.isSuccessful) {
                    val recipes = result.body()
                    if (recipes != null) {
                        recipeDatabase.recipeDao().getRecipes()
                        recipeDatabase.recipeDao().insertAll(recipes)
                        emit(ApiResponse.Success(recipes))
                    } else
                        emit(ApiResponse.Success(dbRecipes))

                } else
                    emit(ApiResponse.Error(R.string.no_internet,dbRecipes))


            } catch (e: Exception) {
                Timber.e(e)
                emit(ApiResponse.Error(R.string.no_internet,dbRecipes))

            }
        }
    }
}