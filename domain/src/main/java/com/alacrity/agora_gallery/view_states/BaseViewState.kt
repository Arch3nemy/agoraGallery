package com.alacrity.agora_gallery.view_states

sealed interface BaseViewState {

    fun getBaseState(): BaseViewState = Loading

    companion object {
        data object Loading : BaseViewState
    }
}