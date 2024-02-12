package com.alacrity.template.di

import com.alacrity.template.use_cases.GetSimpleResponseUseCase
import com.alacrity.template.use_cases.GetSimpleResponseUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun bindNewMessageReceivedUseCase(impl: GetSimpleResponseUseCaseImpl): GetSimpleResponseUseCase

}