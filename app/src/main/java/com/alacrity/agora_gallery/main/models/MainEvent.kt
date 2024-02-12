package com.alacrity.agora_gallery.main.models

import com.alacrity.agora_gallery.BaseEvent
import com.alacrity.agora_gallery.entity.Image
import com.alacrity.agora_gallery.main.MainViewModel

sealed class MainEvent: BaseEvent {

    data object EnterScreen : MainEvent()
    data class EnterDetailsScreen(val image: Image) : MainEvent()
    data object BackToHomeScreen : MainEvent()

}

fun MainViewModel.enterScreen() {
    obtainEvent(com.alacrity.agora_gallery.main.models.MainEvent.EnterScreen)
}

fun MainViewModel.backToHomeScreen() {
    obtainEvent(com.alacrity.agora_gallery.main.models.MainEvent.BackToHomeScreen)
}

fun MainViewModel.enterDetailsScreen(image: Image) {
    obtainEvent(
        com.alacrity.agora_gallery.main.models.MainEvent.EnterDetailsScreen(
            image
        )
    )
}

