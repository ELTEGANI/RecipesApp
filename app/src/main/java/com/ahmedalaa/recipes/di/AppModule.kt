package com.ahmedalaa.recipes.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ahmedalaa.recipes.R
import com.ahmedalaa.recipes.data.local.RecipeDatabase
import com.ahmedalaa.recipes.data.remote.RecipeApi
import com.ahmedalaa.recipes.utils.RequestInterceptor
import com.ahmedalaa.recipes.other.Constant
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
    fun provideRoomDb(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, RecipeDatabase::class.java, "recipe-db")
            .fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideOkHttp() = OkHttpClient().newBuilder()
        .addInterceptor(RequestInterceptor())

    @Singleton
    @Provides
    fun provideMoshi() = Moshi.Builder().build()

    @Singleton
    @Provides
    fun provideGlide(@ApplicationContext context: Context) = Glide.with(context)
        .applyDefaultRequestOptions(RequestOptions().placeholder(R.drawable.progress_animation))


    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi, okHttpClient: OkHttpClient) = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Singleton
    @Provides
    fun provideGamesApi(retrofit: Retrofit) = retrofit.create(RecipeApi::class.java)


}