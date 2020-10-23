package com.ahmedalaa.recipes.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.ahmedalaa.recipes.R
import com.ahmedalaa.recipes.databinding.ActivityMainBinding
import com.ahmedalaa.recipes.utils.ReceiptFragmentFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ToolbarTitleListener {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var fragmentFactory: ReceiptFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.fragmentFactory = fragmentFactory
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(binding.toolbar)
        binding.toolbar
        setupWithNavController(binding.toolbar, findNavController(R.id.nav_host_fragment))
        Navigation.findNavController(this, R.id.nav_host_fragment)
            .addOnDestinationChangedListener { controller, destination, arguments ->
                if (destination.id == R.id.recipeListFragment) {
                    updateTitle(getString(R.string.app_name))
                }
            }
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
