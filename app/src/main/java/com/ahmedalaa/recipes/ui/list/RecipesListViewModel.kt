package com.ahmedalaa.recipes.ui.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmedalaa.recipes.data.model.Recipe
import com.ahmedalaa.recipes.domain.GetRecipes
import com.ahmedalaa.recipes.repository.IRecipesRepository
import com.ahmedalaa.recipes.utils.ApiResponse
import com.ahmedalaa.recipes.utils.bind
import com.ahmedalaa.recipes.utils.loadWithLiveData

class RecipesListViewModel @ViewModelInject constructor(
    private val repository: IRecipesRepository
) : ViewModel() {

    val recipes = MutableLiveData<ApiResponse<List<Recipe>>>()
    lateinit var GetRecipesUseCase:GetRecipes
    init {
        getRecipes()
    }

    private fun getRecipes() {
        GetRecipesUseCase.loadWithLiveData(recipes)
        repository.getRecipes().bind(viewModelScope, recipes)


    }
}