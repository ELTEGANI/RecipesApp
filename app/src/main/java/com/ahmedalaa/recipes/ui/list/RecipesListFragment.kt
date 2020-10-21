package com.ahmedalaa.recipes.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.ahmedalaa.recipes.data.model.Recipe
import com.ahmedalaa.recipes.databinding.FragmentRecipesListBinding
import com.ahmedalaa.recipes.ui.list.adapter.RecipesListAdapter
import com.ahmedalaa.recipes.utils.extenstion.hide
import com.ahmedalaa.recipes.utils.extenstion.show
import com.ahmedalaa.recipes.utils.onError
import com.ahmedalaa.recipes.utils.onProgress
import com.ahmedalaa.recipes.utils.onSuccess
import dagger.hilt.android.AndroidEntryPoint

/**
 * A fragment representing a list of Items.
 */
@AndroidEntryPoint
class RecipesListFragment constructor(
    private var recipeAdapter: RecipesListAdapter,
    var recipesListViewModel: RecipesListViewModel? = null
) : Fragment() {

    private lateinit var bindingLayout: FragmentRecipesListBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = FragmentRecipesListBinding.inflate(layoutInflater, container, false)
        recipesListViewModel = recipesListViewModel ?: ViewModelProvider(requireActivity()).get(
            RecipesListViewModel::class.java
        )
        this.bindingLayout = view
        return view.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingLayout.list.adapter = recipeAdapter

        postponeEnterTransition()
        bindingLayout.list.viewTreeObserver.addOnPreDrawListener {
            startPostponedEnterTransition()
            true
        }

        recipeAdapter.setOnItemClickListener { recipe: Recipe, imageView: ImageView ->
            val extras = FragmentNavigatorExtras(
                imageView to (recipe.image ?: "aa")
            )
            findNavController()
                .navigate(RecipesListFragmentDirections.actionRecipeFragmentToRecipeDetailsFragment(recipe), extras)
        }

        recipesListViewModel?.recipes?.observe(viewLifecycleOwner) { it ->
            it.onProgress {
                bindingLayout.list.hide()
                bindingLayout.loadingDialog.show()
            }.onSuccess {
                if (it.isEmpty()) {
                    bindingLayout.noDataAlert.show()
                    bindingLayout.list.hide()
                } else
                    bindingLayout.list.show()
                bindingLayout.loadingDialog.hide()
                recipeAdapter.recipe = it
            }.onError { errorMsg: Int, list: List<Recipe>? ->
                recipeAdapter.recipe = list ?: emptyList()
                bindingLayout.loadingDialog.hide()
            }
        }
    }
}
