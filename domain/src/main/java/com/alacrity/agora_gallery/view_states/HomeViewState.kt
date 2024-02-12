package com.alacrity.agora_gallery.view_states

import com.alacrity.agora_gallery.entity.Image


sealed class MainViewState: BaseViewState {
    data object Loading : MainViewState()
    data class Error(val exception: Throwable? = null, val message: String = "") : MainViewState()
    data class HomeScreen(val images: List<Image>) : MainViewState()
    data class DetailsScreen(val image: Image) : MainViewState()
}