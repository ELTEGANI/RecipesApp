package com.ahmedalaa.recipes.ui.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.ahmedalaa.recipes.MainCoroutinesRule
import com.ahmedalaa.recipes.TestUtils
import com.ahmedalaa.recipes.getOrAwaitValue
import com.ahmedalaa.recipes.repository.RecipesRepository
import com.ahmedalaa.recipes.utils.ApiResponse
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class RecipesListViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutinesRule = MainCoroutinesRule()

    @MockK
    lateinit var repository: RecipesRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `fetch recipes test returns success response `() = runBlockingTest {
        every { repository.getRecipes() } returns flow {
            emit(ApiResponse.InProgress)
            emit(ApiResponse.Success(TestUtils.recipesList()))
        }
        val recipesListViewModel = RecipesListViewModel(repository, SavedStateHandle())
        val result = recipesListViewModel.recipes.getOrAwaitValue()
        Truth.assertThat(result).isEqualTo(ApiResponse.Success(TestUtils.recipesList()))
    }

    @Test
    fun `fetch recipes test returns error response `() = runBlockingTest {
        every { repository.getRecipes() } returns flow {
            emit(ApiResponse.InProgress)
            emit(ApiResponse.Error(123, null))
        }
        val recipesListViewModel = RecipesListViewModel(repository, SavedStateHandle())
        val result = recipesListViewModel.recipes.getOrAwaitValue()
        Truth.assertThat(result).isEqualTo(ApiResponse.Error(123, null))
    }
}
