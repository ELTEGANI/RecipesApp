package com.ahmedalaa.recipes.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.ahmedalaa.recipes.R
import com.ahmedalaa.recipes.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ToolbarTitleListener {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(binding.toolbar)
        setupWithNavController(binding.toolbar, findNavController(R.id.nav_host_fragment))
    }

    override fun onSupportNavigateUp(): Boolean {
        val navigationController = Navigation.findNavController(this, R.id.nav_host_fragment)
        return navigationController.navigateUp()

    }

    override fun onBackPressed() {
        super.onBackPressed()
        val navigationController = Navigation.findNavController(this, R.id.nav_host_fragment)
        navigationController.navigateUp()

    }

    override fun updateTitle(title: String) {
        binding.title = title

    }


}

interface ToolbarTitleListener {
    fun updateTitle(title: String)
}