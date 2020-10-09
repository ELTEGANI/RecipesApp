package com.ahmedalaa.recipes.di

import android.content.Context
import androidx.room.Room
import com.ahmedalaa.recipes.R
import com.ahmedalaa.recipes.data.local.RecipeDatabase
import com.ahmedalaa.recipes.data.remote.RecipeApi
import com.ahmedalaa.recipes.other.Constant
import com.ahmedalaa.recipes.repository.IRecipesRepository
import com.ahmedalaa.recipes.repository.RecipesRepository
import com.ahmedalaa.recipes.utils.RequestInterceptor
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRoomDb(@ApplicationContext context: Context): RecipeDatabase =
        Room.databaseBuilder(context, RecipeDatabase::class.java, "recipe-db")
            .fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideOkHttp() = OkHttpClient().newBuilder()
        .addInterceptor(RequestInterceptor())
        .build()

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Singleton
    @Provides
    fun provideGlide(@ApplicationContext context: Context) = Glide.with(context)
        .applyDefaultRequestOptions(RequestOptions().placeholder(R.drawable.progress_animation))

    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Singleton
    @Provides
    fun provideGamesApi(retrofit: Retrofit): RecipeApi = retrofit.create(RecipeApi::class.java)

    @Singleton
    @Provides
    fun provideRecipesRepository(
        recipeDatabase: RecipeDatabase,
        recipeApi: RecipeApi
    ): IRecipesRepository = RecipesRepository(recipeDatabase, recipeApi)
}