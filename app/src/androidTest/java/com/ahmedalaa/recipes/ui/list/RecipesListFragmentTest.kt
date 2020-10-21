package com.ahmedalaa.recipes.ui.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.ahmedalaa.recipes.R
import com.ahmedalaa.recipes.TestUtils
import com.ahmedalaa.recipes.data.model.Recipe
import com.ahmedalaa.recipes.repository.RecipesRepository
import com.ahmedalaa.recipes.ui.list.adapter.RecipesListAdapter
import com.ahmedalaa.recipes.utils.ApiResponse
import com.ahmedalaa.recipes.utils.TestReceiptFragmentFactory
import com.ahmedalaa.recipes.utils.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
@HiltAndroidTest
@MediumTest
@ExperimentalCoroutinesApi
class RecipesListFragmentTest {

    @get:Rule()
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    lateinit var recipesListViewModel: RecipesListViewModel

    @After
    fun unregister() {
    }

    @Before
    fun setUp() {
//        MockitoAnnotations.initMocks(this);

//        navCompiler= mock(NavController::class.java)
        // IdlingRegistry.getInstance().register(EspressoUriIdlingResource.uriIdlingResource)
    }

    @After
    fun tearDown() {

//        IdlingRegistry.getInstance().unregister(EspressoUriIdlingResource.uriIdlingResource)
    }

    @Test
    fun TestRecipesDataIsVisable() {
        val repository: RecipesRepository = mock(RecipesRepository::class.java)

        `when`(repository.getRecipes()).thenReturn(
            flow {
                emit(ApiResponse.InProgress)
                emit(ApiResponse.Success(TestUtils.recipesList()))
            }
        )

        val recipesListViewModel = RecipesListViewModel(repository, SavedStateHandle())
        val fragmentFactory = TestReceiptFragmentFactory(RecipesListAdapter(), recipesListViewModel)
        launchFragmentInHiltContainer<RecipesListFragment>(fragmentFactory = fragmentFactory) {
            this.recipesListViewModel = RecipesListViewModel(repository, SavedStateHandle())
        }
        onView(ViewMatchers.withId(R.id.list)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }

    @Test
    fun TestRecipesNoDataIsVisable() {
        val repository: RecipesRepository = mock(RecipesRepository::class.java)

        `when`(repository.getRecipes()).thenReturn(
            flow {
                emit(ApiResponse.InProgress)
                emit(ApiResponse.Success(emptyList<Recipe>()))
            }
        )

        val recipesListViewModel = RecipesListViewModel(repository, SavedStateHandle())
        val fragmentFactory = TestReceiptFragmentFactory(RecipesListAdapter(), recipesListViewModel)
        launchFragmentInHiltContainer<RecipesListFragment>(fragmentFactory = fragmentFactory) {
            this.recipesListViewModel = RecipesListViewModel(repository, SavedStateHandle())
        }
        onView(ViewMatchers.withId(R.id.no_data_alert)).check(
            matches(
                withEffectiveVisibility(
                    ViewMatchers.Visibility.VISIBLE
                )
            )
        )
    }

    @Test
    fun TestOnRecipeItemclick() {
        val navController = mock(NavController::class.java)

        val repository: RecipesRepository = mock(RecipesRepository::class.java)
        `when`(repository.getRecipes()).thenReturn(
            flow {
                emit(ApiResponse.InProgress)
                emit(ApiResponse.Success(TestUtils.recipesList()))
            }
        )

        val recipesListViewModel = RecipesListViewModel(repository, SavedStateHandle())
        val fragmentFactory = TestReceiptFragmentFactory(RecipesListAdapter(), recipesListViewModel)
        launchFragmentInHiltContainer<RecipesListFragment>(fragmentFactory = fragmentFactory) {
            Navigation.setViewNavController(requireView(), navController)

            this.recipesListViewModel = RecipesListViewModel(repository, SavedStateHandle())
        }

        onView(withId(R.id.list)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecipesListAdapter.ViewHolder>(0, ViewActions.click())
        )

        verify(navController).navigate(R.id.action_recipeFragment_to_recipeDetailsFragment)
//        onView(withId(R.id.recipe_description)).check(matches(isDisplayed()))
    }
}
