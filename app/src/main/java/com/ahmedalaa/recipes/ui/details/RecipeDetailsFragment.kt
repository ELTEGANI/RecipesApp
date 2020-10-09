package com.ahmedalaa.recipes.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.ahmedalaa.recipes.R
import com.ahmedalaa.recipes.databinding.FragmentRecipesDetailsBinding
import com.ahmedalaa.recipes.ui.ToolbarTitleListener

class RecipeDetailsFragment : Fragment() {

    private lateinit var bindingLayout: FragmentRecipesDetailsBinding
    private val args: RecipeDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val transition =
            TransitionInflater.from(context).inflateTransition(R.transition.image_transform)
        sharedElementEnterTransition = transition
        args.recipe.name?.let { (requireActivity() as ToolbarTitleListener).updateTitle(it) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewCompat.setTransitionName(bindingLayout.recipeImg, args.recipe.image)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = FragmentRecipesDetailsBinding.inflate(layoutInflater, container, false)
        this.bindingLayout = view
        view.recipeItem = args.recipe

        return view.root
    }
}