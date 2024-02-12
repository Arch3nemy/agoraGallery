package com.alacrity.agora_gallery.di

import com.alacrity.agora_gallery.App
import com.alacrity.agora_gallery.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ApiModule::class, UseCaseModule::class, RepositoryModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)

    fun inject(app: App)

}