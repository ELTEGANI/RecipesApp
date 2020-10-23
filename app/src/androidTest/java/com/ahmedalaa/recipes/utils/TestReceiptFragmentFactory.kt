package com.ahmedalaa.recipes.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.ahmedalaa.recipes.ui.list.RecipesListFragment
import com.ahmedalaa.recipes.ui.list.RecipesListViewModel
import com.ahmedalaa.recipes.ui.list.adapter.RecipesListAdapter
import javax.inject.Inject

class TestReceiptFragmentFactory @Inject constructor(
    val recipesListAdapter: RecipesListAdapter,
    val recipesListViewModel: RecipesListViewModel

) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            RecipesListFragment::class.java.name -> RecipesListFragment(recipesListAdapter, recipesListViewModel)
            else -> {
                super.instantiate(classLoader, className)
            }
        }
    }
}
