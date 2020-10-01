

package com.ahmedalaa.recipes.data.remote

import com.ahmedalaa.recipes.data.model.Recipe
import retrofit2.Response
import retrofit2.http.GET

interface RecipeApi {

  @GET("recipes.json")
  suspend fun fetchRecipeList(): Response<List<Recipe>>
}
