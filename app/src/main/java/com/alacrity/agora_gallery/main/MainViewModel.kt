package com.alacrity.agora_gallery.main

import com.alacrity.agora_gallery.main.models.MainEvent
import com.alacrity.agora_gallery.util.BaseViewModel
import com.alacrity.agora_gallery.use_cases.GetImagesUseCase
import com.alacrity.agora_gallery.view_states.MainViewState
import com.alacrity.agora_gallery.view_states.MainViewState.Error
import com.alacrity.agora_gallery.view_states.MainViewState.HomeScreen
import com.alacrity.agora_gallery.view_states.MainViewState.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import com.alacrity.agora_gallery.app.BuildConfig
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getImagesUseCase: GetImagesUseCase
) : BaseViewModel<MainEvent, MainViewState>(Loading) {

    val viewState: StateFlow<MainViewState> = _viewState


    override fun obtainEvent(event: MainEvent) {
        when (val currentState = _viewState.value) {
            is Loading -> currentState.reduce(event)
            is Error -> currentState.reduce(event)
            is HomeScreen -> currentState.reduce(event)
            is MainViewState.DetailsScreen -> currentState.reduce(event)
        }
    }

    private fun Loading.reduce(event: MainEvent) {
        logReduce(event)
        when (event) {
            MainEvent.EnterScreen -> {
                loadPhotos()
            }
            else -> Unit
        }
    }

    private fun Error.reduce(event: MainEvent) {
        logReduce(event)

    }

    private fun MainViewState.DetailsScreen.reduce(event: MainEvent) {
        logReduce(event)
        when(event) {
            is MainEvent.BackToHomeScreen -> {
                loadPhotos()
            }
            else -> Unit
        }
    }

    private fun HomeScreen.reduce(event: MainEvent) {
        logReduce(event)
        when(event) {
            is MainEvent.EnterDetailsScreen -> {
                _viewState.update { MainViewState.DetailsScreen(event.image) }
            }
            else -> Unit
        }
    }

    private fun loadPhotos() {
        launch(
            logError = "Error Getting photos",
            logSuccess = "Successfully received photos",
            onSuccess = { response ->
                _viewState.update { HomeScreen(response) }
            },
            onFailure = { exception ->
                _viewState.update { Error(exception, exception.message ?: "No message provided") }
            }
        ) {
            getImagesUseCase(BuildConfig.ACCESS_KEY)
        }

    }

}