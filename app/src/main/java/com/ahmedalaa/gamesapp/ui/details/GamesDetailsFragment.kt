package com.ahmedalaa.gamesapp.ui.details

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ahmedalaa.gamesapp.R

class GamesDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = GamesDetailsFragment()
    }

    private lateinit var viewModel: GamesDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.games_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GamesDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}