package com.ahmedalaa.recipes.data.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import androidx.test.runner.AndroidJUnit4
import com.ahmedalaa.recipes.MainCoroutinesRule
import com.ahmedalaa.recipes.TestUtils
import com.ahmedalaa.recipes.data.remote.RecipeApi
import com.ahmedalaa.recipes.di.AppModule
import com.ahmedalaa.recipes.enqueueRequest
import com.ahmedalaa.recipes.utils.Utils
import com.google.common.truth.Truth
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import javax.inject.Inject
import javax.inject.Named
import kotlin.jvm.Throws


@SmallTest
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class RecipeApiTest {
    @get:Rule()
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val mainCoroutinesRule = MainCoroutinesRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var mockWebServer: MockWebServer

    @Inject
    @Named("test-recipeApi")
    lateinit var recipeApi: RecipeApi

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    @Throws(IOException::class)
    fun testFetchRecipeList() = runBlocking {
        mockWebServer.enqueueRequest("response.json",200)
        val response = recipeApi.fetchRecipeList()
        mockWebServer.takeRequest()
        Truth.assertThat(response.body()).hasSize(2)
    }



}