package com.alacrity.agora_gallery

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.alacrity.agora_gallery.theme.AppTheme
import com.alacrity.agora_gallery.main.MainViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun AgoraGalleryApp(
    homeViewModel: MainViewModel
) {
    AppTheme {
            val systemUiController = rememberSystemUiController()
            val darkIcons = MaterialTheme.colors.isLight
            SideEffect {
                systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = darkIcons)
            }

            AppNavGraph(
                homeViewModel = homeViewModel,
            )
        }

}