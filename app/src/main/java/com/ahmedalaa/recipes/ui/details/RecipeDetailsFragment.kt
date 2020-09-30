package com.ahmedalaa.recipes.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.ahmedalaa.recipes.databinding.FragmentRecipesDetailsBinding
import java.util.concurrent.TimeUnit

class RecipeDetailsFragment : Fragment() {

    private lateinit var bindingLayout: FragmentRecipesDetailsBinding
    val args: RecipeDetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = FragmentRecipesDetailsBinding.inflate(layoutInflater, container, false)
        this.bindingLayout = view
        view.recipeItem = args.recipe
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        postponeEnterTransition(250, TimeUnit.MILLISECONDS)

        return view.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

}