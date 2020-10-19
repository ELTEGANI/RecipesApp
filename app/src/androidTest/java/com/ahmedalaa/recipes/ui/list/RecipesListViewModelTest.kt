package com.ahmedalaa.recipes.ui.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import androidx.test.runner.AndroidJUnit4
import com.ahmedalaa.recipes.MainCoroutinesRule
import com.ahmedalaa.recipes.TestUtils
import com.ahmedalaa.recipes.di.AppModule
import com.ahmedalaa.recipes.getOrAwaitValue
import com.ahmedalaa.recipes.utils.ApiResponse
import com.google.common.truth.Truth
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`

@SmallTest
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class RecipesListViewModelTest {

    @get:Rule()
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val mainCoroutinesRule = MainCoroutinesRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
//     lateinit var repository:IRecipesRepository

    @Before
    fun setUp() {
    }


    @After
    fun tearDown() {
    }

   /* @Test
    fun getRecipesReturnSuccessData()= runBlockingTest {
        `when`(repository.getRecipes()).thenReturn(flow {
            emit(ApiResponse.InProgress)
            emit(ApiResponse.Success(TestUtils.recipesList()))
        })
        val recipesListViewModel=RecipesListViewModel(repository)
        val result=recipesListViewModel.recipes.getOrAwaitValue()
        Truth.assertThat(result).isSameInstanceAs(ApiResponse.Success::class)
    }

    @Test
    fun getRecipesReturnFailedData()= runBlockingTest {
        `when`(repository.getRecipes()).thenReturn(flow {
            emit(ApiResponse.InProgress)
            emit(ApiResponse.Error(123,null))
        })
        val recipesListViewModel=RecipesListViewModel(repository)
        val result=recipesListViewModel.recipes.getOrAwaitValue()
        Truth.assertThat(result).isSameInstanceAs(ApiResponse.Error::class)
    }*/
}