package com.ahmedalaa.recipes

import androidx.appcompat.app.AppCompatActivity
import com.ahmedalaa.recipes.ui.ToolbarTitleListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HiltTestActivity : AppCompatActivity(), ToolbarTitleListener {
    override fun updateTitle(title: String) {}
}
