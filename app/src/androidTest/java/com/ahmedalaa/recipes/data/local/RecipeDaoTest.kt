package com.ahmedalaa.recipes.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.ahmedalaa.recipes.TestUtils
import com.google.common.truth.Truth
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@SmallTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class RecipeDaoTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test-db")
    lateinit var db: RecipeDatabase

    private lateinit var recipeDao: RecipeDao

    @Before
    fun setUp() {
        hiltRule.inject()
        recipeDao = db.recipeDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertRecipesAndLoad() = runBlockingTest {
        recipeDao.insert(TestUtils.recipe)
        val dataInsertedList = recipeDao.getRecipes()
        Truth.assertThat(dataInsertedList).contains(TestUtils.recipe)
    }
    @Test
    fun insertAllRecipesAndLoad() = runBlockingTest {
        recipeDao.insertAll(TestUtils.recipesList())
        val dataInsertedList = recipeDao.getRecipes()
        Truth.assertThat(dataInsertedList).hasSize(2)
    }

    @Test
    fun insertAndDelete() = runBlockingTest {
        recipeDao.insert(recipe = TestUtils.recipe)
        recipeDao.delete(recipe = TestUtils.recipe)

        val dataInsertedList = recipeDao.getRecipes()
        Truth.assertThat(dataInsertedList).isEmpty()
    }
    @Test
    fun insertAndDeleteAll() = runBlockingTest {
        recipeDao.insert(recipe = TestUtils.recipe)
        recipeDao.deleteAll()
        val dataInsertedList = recipeDao.getRecipes()
        Truth.assertThat(dataInsertedList).isEmpty()
    }
}
