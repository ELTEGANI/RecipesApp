package com.ahmedalaa.gamesapp.di

import android.content.Context
import com.ahmedalaa.gamesapp.R
import com.ahmedalaa.gamesapp.data.remote.GamesApi
import com.ahmedalaa.gamesapp.utils.RequestInterceptor
import com.ahmedalaa.gamesapp.other.Constant
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
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideOkHttp() = OkHttpClient().newBuilder()
        .addInterceptor(RequestInterceptor())
        .callTimeout(2, TimeUnit.MINUTES).build()

    @Singleton
    @Provides
    fun provideMoshi() = Moshi.Builder().build()

    @Singleton
    @Provides
    fun provideGlide(@ApplicationContext context:Context) = Glide.with(context)
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
    fun provideGamesApi(retrofit: Retrofit)=retrofit.create(GamesApi::class.java)


}