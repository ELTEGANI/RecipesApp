package com.ahmedalaa.recipes.di

import android.content.Context
import androidx.room.Room
import com.ahmedalaa.recipes.data.local.RecipeDatabase
import com.ahmedalaa.recipes.data.remote.RecipeApi
import com.ahmedalaa.recipes.repository.RecipesRepository
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object TestAppModule {


    @Singleton
    @Provides
    @Named("test-db")
    fun provideRoomDb(@ApplicationContext context: Context): RecipeDatabase =
        Room.inMemoryDatabaseBuilder(context, RecipeDatabase::class.java)
            .fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideMockServer()=MockWebServer()



    @Singleton
    @Provides
    @Named("test-retrofit")
    fun provideRetrofit(  moshi: Moshi, okHttpClient: OkHttpClient, mockWebServer: MockWebServer): Retrofit = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()


    @Singleton
    @Provides
    @Named("test-recipeApi")
    fun provideRecipeApi(@Named("test-retrofit")retrofit: Retrofit): RecipeApi = retrofit.create(RecipeApi::class.java)

    @Singleton
    @Provides
    @Named("test")
    fun provideRecipesRepository(
        @Named("test-db")
        recipeDatabase: RecipeDatabase,
        @Named("test-recipeApi")
        recipeApi: RecipeApi
    ): RecipesRepository = RecipesRepository(recipeDatabase, recipeApi)

}
