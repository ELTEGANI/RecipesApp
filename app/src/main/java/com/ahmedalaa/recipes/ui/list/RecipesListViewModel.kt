package com.ahmedalaa.recipes.ui.list

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmedalaa.recipes.data.model.Recipe
import com.ahmedalaa.recipes.repository.RecipesRepository
import com.ahmedalaa.recipes.utils.ApiResponse
import com.ahmedalaa.recipes.utils.bind

class RecipesListViewModel @ViewModelInject constructor(
    private val repository: RecipesRepository,
    @Assisted private val savedStateHandle: SavedStateHandle

) : ViewModel() {

    val recipes = MutableLiveData<ApiResponse<List<Recipe>>>()

    init {
        getRecipes()
    }

    fun getRecipes() {
        repository.getRecipes().bind(viewModelScope, recipes)
    }
}
