package com.ahmedalaa.recipes.utils.extenstion

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.ahmedalaa.recipes.di.AppModule
import com.ahmedalaa.recipes.di.MyCustomComponentBuilder
import javax.inject.Inject

@BindingAdapter("load")
fun ImageView.load( link: String) {
    AppModule.provideGlide(this.context).load(link).into(this)
}
@BindingAdapter("hideIfEmpty")
fun TextView.hide(link: String?) {
    if (link.isNullOrEmpty()) {
        visibility= View.GONE
    }
}