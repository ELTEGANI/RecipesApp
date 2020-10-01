package com.ahmedalaa.recipes.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.ahmedalaa.recipes.R
import com.ahmedalaa.recipes.data.model.Recipe
import com.ahmedalaa.recipes.databinding.FragmentRecipesListBinding
import com.ahmedalaa.recipes.ui.ToolbarTitleListener
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
class RecipesListFragment : Fragment() {


    private lateinit var bindingLayout: FragmentRecipesListBinding
    private val recipesListViewModel: RecipesListViewModel by viewModels()
    private val adapter = RecipesListAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = FragmentRecipesListBinding.inflate(layoutInflater, container, false)

        this.bindingLayout = view
        return view.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingLayout.list.adapter = adapter

        postponeEnterTransition()
        bindingLayout.list.viewTreeObserver.addOnPreDrawListener {
            startPostponedEnterTransition()
            true
        }

        adapter.setOnItemClickListener { recipe: Recipe, imageView: ImageView ->
            val extras = FragmentNavigatorExtras(
                imageView to (recipe.image ?: "aa")
            )
            findNavController().navigate(
                RecipesListFragmentDirections.actionGamesFragmentToGamesDetailsFragment(
                    recipe,
                ), extras
            )
        }

        recipesListViewModel.recipes.observe(viewLifecycleOwner) { it ->
            it.onProgress {
                bindingLayout.loadingDialog.show()
            }.onSuccess {
                bindingLayout.loadingDialog.hide()
                adapter.recipe = it

            }.onError { errorMsg: Int, list: List<Recipe>? ->
                adapter.recipe = list ?: emptyList()

                bindingLayout.loadingDialog.hide()

            }
        }
        (requireActivity() as ToolbarTitleListener).updateTitle(getString(R.string.recipes))


    }

}