package com.ahmedalaa.recipes

import com.ahmedalaa.recipes.data.model.Recipe
import okio.buffer
import okio.source
import java.nio.charset.Charset


object TestUtils {
    val recipe = Recipe(
        "516 kcal",
        "47 g",
        "",
        "There’s nothing like the simple things in life - the smell of freshly cut grass, sitting outside on a nice sunny day, spending time with friends and family. Well here is a recipe that delivers simple culinary pleasures - some nice fresh fish with a crispy crust, crunchy potato wedges and some delightfully sweet sugar snap peas flavoured with cooling mint. Slip into something comfortable and relax into a delicious dinner!",
        0,
        "8 g",
        "with Sweet Potato Wedges and Minted Snap Peas",
        "533143aaff604d567f8b4571",
        "https://img.hellofresh.com/f_auto,q_auto/hellofresh_s3/image/533143aaff604d567f8b4571.jpg",
        "Crispy Fish Goujons",
        "43 g",
        "https://img.hellofresh.com/f_auto,q_auto,w_300/hellofresh_s3/image/533143aaff604d567f8b4571.jpg",
        "PT35M"
    )
    val recipe2 = Recipe(
        "516 kcal",
        "47 g",
        "",
        "There’s nothing like the simple things in life - the smell of freshly cut grass, sitting outside on a nice sunny day, spending time with friends and family. Well here is a recipe that delivers simple culinary pleasures - some nice fresh fish with a crispy crust, crunchy potato wedges and some delightfully sweet sugar snap peas flavoured with cooling mint. Slip into something comfortable and relax into a delicious dinner!",
        0,
        "8 g",
        "with Sweet Potato Wedges and Minted Snap Peas",
        "533143aaff604d567f8b457122",
        "https://img.hellofresh.com/f_auto,q_auto/hellofresh_s3/image/533143aaff604d567f8b4571.jpg",
        "Crispy Fish Goujons",
        "43 g",
        "https://img.hellofresh.com/f_auto,q_auto,w_300/hellofresh_s3/image/533143aaff604d567f8b4571.jpg",
        "PT35M"
    )
    fun recipesList():List<Recipe>{
        return listOf(recipe, recipe2)
    }


}