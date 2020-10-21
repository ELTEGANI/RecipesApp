package com.ahmedalaa.recipes.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.ahmedalaa.recipes.ui.list.RecipesListFragment
import com.ahmedalaa.recipes.ui.list.adapter.RecipesListAdapter
import javax.inject.Inject

class ReceiptFragmentFactory @Inject constructor(
    private val recipesListAdapter: RecipesListAdapter
) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            RecipesListFragment::class.java.name -> RecipesListFragment(recipesListAdapter)
            else -> {
                super.instantiate(classLoader, className)
            }
        }
    }
}
