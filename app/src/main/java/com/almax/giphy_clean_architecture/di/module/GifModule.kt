package com.almax.giphy_clean_architecture.di.module

import com.almax.giphy_clean_architecture.presentation.gif.GifAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class GifModule {

    @ActivityScoped
    @Provides
    fun provideGifAdapter(): GifAdapter =
        GifAdapter(arrayListOf())
}