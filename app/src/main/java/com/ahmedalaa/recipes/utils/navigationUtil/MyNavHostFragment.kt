package com.ahmedalaa.recipes.utils.navigationUtil

import androidx.navigation.fragment.NavHostFragment

class MyNavHostFragment : NavHostFragment() {
    override fun createFragmentNavigator() =
        MyFragmentNavigator(requireContext(), childFragmentManager, id)
}