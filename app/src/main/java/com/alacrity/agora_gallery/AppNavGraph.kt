package com.alacrity.agora_gallery

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alacrity.agora_gallery.Destinations.HOME_ROUTE
import com.alacrity.agora_gallery.main.MainViewModel

object Destinations {
    const val HOME_ROUTE = "home"
}


/**
 * Just because only two screens are present, there's no need to make an
 * additional route for the second one using NavController
 */
@Composable
fun AppNavGraph(
    homeViewModel: MainViewModel,
    navController: NavHostController = rememberNavController(),
    startDestination: String = HOME_ROUTE,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable(HOME_ROUTE) {
            MainScreen(
                viewModel = homeViewModel,
            )
        }
    }
}