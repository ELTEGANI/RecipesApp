package com.ahmedalaa.recipes.ui.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.ahmedalaa.recipes.MainCoroutinesRule
import com.ahmedalaa.recipes.TestUtils
import com.ahmedalaa.recipes.getOrAwaitValue
import com.ahmedalaa.recipes.repository.RecipesRepository
import com.ahmedalaa.recipes.utils.ApiResponse
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
class RecipesListViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutinesRule = MainCoroutinesRule()

    lateinit var repository: RecipesRepository

    @Before
    fun setUp() {
        repository = mock(RecipesRepository::class.java)

//        val flow= flow {
//            emit(ApiResponse.InProgress)
//            emit(ApiResponse.Success<List<Recipe>>(TestUtils.recipesList()))
//
//        }
//        `when`(repository.getRecipes()).thenReturn(flow)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getRecipesReturnSuccessData() = runBlockingTest {
        `when`(repository.getRecipes()).thenReturn(
            flow {
                emit(ApiResponse.InProgress)
                emit(ApiResponse.Success(TestUtils.recipesList()))
            }
        )
        val recipesListViewModel = RecipesListViewModel(repository, SavedStateHandle())
        val result = recipesListViewModel.recipes.getOrAwaitValue()
        Truth.assertThat(result).isEqualTo(ApiResponse.Success(TestUtils.recipesList()))
    }

    @Test
    fun getRecipesReturnFailedData() = runBlockingTest {
        `when`(repository.getRecipes()).thenReturn(
            flow {
                emit(ApiResponse.InProgress)
                emit(ApiResponse.Error(123, null))
            }
        )
        val recipesListViewModel = RecipesListViewModel(repository, SavedStateHandle())
        val result = recipesListViewModel.recipes.getOrAwaitValue()
        Truth.assertThat(result).isEqualTo(ApiResponse.Error(123, null))
    }
}
