package com.ahmedalaa.recipes.utils.extenstion

import com.ahmedalaa.recipes.data.local.RecipeEntity
import com.ahmedalaa.recipes.data.remote.response.RecipeItem
import com.ahmedalaa.recipes.data.remote.response.RecipeResponse
import com.ahmedalaa.recipes.model.Recipe

fun RecipeEntity.toRecipe(): Recipe {
    return Recipe(
        calories,
        carbos,
        country,
        description,
        difficulty,
        fats,
        headline,
        id,
        image,
        name,
        proteins,
        thumb,
        time
    )
}
fun RecipeItem.toRecipe(): Recipe {
    return Recipe(
        calories,
        carbos,
        country,
        description,
        difficulty,
        fats,
        headline,
        id,
        image,
        name,
        proteins,
        thumb,
        time
    )
}
fun RecipeItem.toRecipeEntity(): RecipeEntity {
    return RecipeEntity(
        calories,
        carbos,
        country,
        description,
        difficulty,
        fats,
        headline,
        id,
        image,
        name,
        proteins,
        thumb,
        time
    )
}
fun RecipeResponse.toRecipesList():List<Recipe>{
    return this.map { recipeItem ->  recipeItem.toRecipe() }
}
fun RecipeResponse.toRecipesEntityList():List<RecipeEntity>{
    return this.map { recipeItem ->  recipeItem.toRecipeEntity() }
}
fun List<RecipeEntity>.toRecipesList():List<Recipe>{
    return this.map { recipeItem ->  recipeItem.toRecipe() }
}


