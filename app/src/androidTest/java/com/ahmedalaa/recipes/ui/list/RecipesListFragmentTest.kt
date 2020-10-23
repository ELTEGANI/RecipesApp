package com.ahmedalaa.recipes.ui.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.ahmedalaa.recipes.R
import com.ahmedalaa.recipes.TestUtils
import com.ahmedalaa.recipes.data.model.Recipe
import com.ahmedalaa.recipes.repository.RecipesRepository
import com.ahmedalaa.recipes.ui.list.adapter.RecipesListAdapter
import com.ahmedalaa.recipes.utils.ApiResponse
import com.ahmedalaa.recipes.utils.TestReceiptFragmentFactory
import com.ahmedalaa.recipes.utils.launchFragmentInHiltContainer
import com.google.common.truth.Truth
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
@HiltAndroidTest
@MediumTest
@ExperimentalCoroutinesApi
class RecipesListFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var repository: RecipesRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun startShowRecipesList() {
        every { repository.getRecipes() } returns flow {
            emit(ApiResponse.InProgress)
            emit(ApiResponse.Success(TestUtils.recipesList()))
        }
        val recipesListViewModel = RecipesListViewModel(repository, SavedStateHandle())
        val fragmentFactory = TestReceiptFragmentFactory(RecipesListAdapter(), recipesListViewModel)
        launchFragmentInHiltContainer<RecipesListFragment>(fragmentFactory = fragmentFactory) {
            this.recipesListViewModel = RecipesListViewModel(repository, SavedStateHandle())
        }
        onView(withId(R.id.list)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }

    @Test
    fun startShowNoData() {

        every { repository.getRecipes() } returns flow {
            emit(ApiResponse.InProgress)
            emit(ApiResponse.Success(emptyList<Recipe>()))
        }
        val recipesListViewModel = RecipesListViewModel(repository, SavedStateHandle())
        val fragmentFactory = TestReceiptFragmentFactory(RecipesListAdapter(), recipesListViewModel)
        launchFragmentInHiltContainer<RecipesListFragment>(fragmentFactory = fragmentFactory) {
            this.recipesListViewModel = RecipesListViewModel(repository, SavedStateHandle())
        }
        onView(withId(R.id.no_data_alert)).check(
            matches(
                withEffectiveVisibility(
                    ViewMatchers.Visibility.VISIBLE
                )
            )
        )
    }

    @Test
    fun clickOnRecipeItem_recipeDetailsShown() {

        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )

        navController.setGraph(R.navigation.nav_graph)
        every { repository.getRecipes() } returns flow {
            emit(ApiResponse.Success(TestUtils.recipesList()))
        }

        val recipesListViewModel = RecipesListViewModel(repository, SavedStateHandle())
        val fragmentFactory = TestReceiptFragmentFactory(RecipesListAdapter(), recipesListViewModel)
        launchFragmentInHiltContainer<RecipesListFragment>(fragmentFactory = fragmentFactory) {
            Navigation.setViewNavController(requireView(), navController)

            this.recipesListViewModel = RecipesListViewModel(repository, SavedStateHandle())
        }

        onView(withId(R.id.list)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecipesListAdapter.ViewHolder>(
                0,
                click()
            )
        )
        Truth.assertThat(navController.currentDestination?.id).isEqualTo(R.id.recipeDetailsFragment)
    }
}
