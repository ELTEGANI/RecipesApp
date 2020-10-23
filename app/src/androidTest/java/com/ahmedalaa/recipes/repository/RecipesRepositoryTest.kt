package com.ahmedalaa.recipes.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.ahmedalaa.recipes.R
import com.ahmedalaa.recipes.data.local.RecipeDatabase
import com.ahmedalaa.recipes.data.model.Recipe
import com.ahmedalaa.recipes.data.remote.RecipeApi
import com.ahmedalaa.recipes.enqueueRequest
import com.ahmedalaa.recipes.utils.ApiResponse
import com.ahmedalaa.recipes.utils.Utils
import com.google.common.truth.Truth
import com.squareup.moshi.Moshi
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject
import javax.inject.Named

@SmallTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class RecipesRepositoryTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    @Inject
    @Named("test-db")
    lateinit var db: RecipeDatabase

    @Inject
    @Named("test-recipeApi")
    lateinit var recipeApi: RecipeApi

    @Inject
    lateinit var mockWebServer: MockWebServer

    @Inject lateinit var moshi: Moshi

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun fetchRecipes_returnsSuccessData(): Unit = runBlocking {
        mockWebServer.enqueueRequest("response.json", 200)
        val recipesRepository = RecipesRepository(db, recipeApi)

        val response = recipesRepository.getRecipes().toList()
        mockWebServer.takeRequest()
        Truth.assertThat(response).containsExactly(
            ApiResponse.InProgress,
            ApiResponse.Success(
                Utils.readFileResponseToListOfObject<Recipe>("response.json", moshi)!!
            )
        )
    }

    @Test
    fun fetchRecipes_returnsErrorData(): Unit = runBlocking {
        mockWebServer.enqueueRequest("response.json", 404)
        val recipesRepository = RecipesRepository(db, recipeApi)

        val response = recipesRepository.getRecipes().toList()
        mockWebServer.takeRequest()
        Truth.assertThat(response).containsExactly(
            ApiResponse.InProgress,
            ApiResponse.Error(
                R.string.no_internet,
                listOf<Recipe>()
            )
        )
    }
}
