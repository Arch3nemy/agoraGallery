package com.alacrity.agora_gallery

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alacrity.agora_gallery.main.MainViewModel
import com.alacrity.agora_gallery.main.models.backToHomeScreen
import com.alacrity.agora_gallery.main.models.enterDetailsScreen
import com.alacrity.agora_gallery.main.models.enterScreen
import com.alacrity.agora_gallery.main.screens.DetailsScreen
import com.alacrity.agora_gallery.main.screens.HomeScreen
import com.alacrity.agora_gallery.view_states.MainViewState

@Composable
fun MainScreen(
    viewModel: MainViewModel,
) {

    val state by viewModel.viewState.collectAsStateWithLifecycle()

    when (state) {
        MainViewState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                LinearProgressIndicator()
            }
        }

        is MainViewState.HomeScreen -> {
            HomeScreen(
                images = (state as MainViewState.HomeScreen).images,
                onImageClick = { image -> viewModel.enterDetailsScreen(image) }
            )
        }

        is MainViewState.DetailsScreen -> {
            DetailsScreen(
                image = (state as MainViewState.DetailsScreen).image,
                onBackClick = { viewModel.backToHomeScreen() }
            )
        }

        is MainViewState.Error -> {
            /* ShowErrorView */
        }

        else -> Unit
    }

    LaunchedEffect(key1 = state, block = {
        viewModel.enterScreen()
    })

}
