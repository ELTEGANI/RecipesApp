package com.ahmedalaa.recipes.ui.details

import android.os.Bundle
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.ahmedalaa.recipes.R
import com.ahmedalaa.recipes.TestUtils
import com.ahmedalaa.recipes.utils.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
@HiltAndroidTest
@MediumTest
@ExperimentalCoroutinesApi
class RecipeDetailsFragmentTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun showRecipeDetails() {
        val bundle = Bundle()
        bundle.putParcelable("recipe", TestUtils.recipesList()[0])
        launchFragmentInHiltContainer<RecipeDetailsFragment>(fragmentArgs = bundle)
        Espresso.onView(withId(R.id.recipe_description)).check(matches(withText(TestUtils.recipesList()[0].description)))
    }
}
