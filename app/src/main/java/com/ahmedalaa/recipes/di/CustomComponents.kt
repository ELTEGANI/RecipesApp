package com.ahmedalaa.recipes.di

import com.bumptech.glide.RequestManager
import com.squareup.moshi.Moshi
import dagger.BindsInstance
import dagger.hilt.DefineComponent
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@DefineComponent(parent = SingletonComponent::class)
interface MyCustomComponent

@DefineComponent.Builder
interface MyCustomComponentBuilder {
    fun fooSeedData(@BindsInstance glide: RequestManager): MyCustomComponentBuilder
    fun build(): MyCustomComponent
}
@EntryPoint
@InstallIn(MyCustomComponent::class)
interface MyCustomEntryPoint {
    fun getGlide(): RequestManager
}