package com.alacrity.agora_gallery.di

import com.alacrity.agora_gallery.use_cases.GetImagesUseCase
import com.alacrity.agora_gallery.use_cases.GetImagesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun bindNewMessageReceivedUseCase(impl: GetImagesUseCaseImpl): GetImagesUseCase

}