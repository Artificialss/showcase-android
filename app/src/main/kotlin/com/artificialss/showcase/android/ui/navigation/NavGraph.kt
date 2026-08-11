package com.artificialss.showcase.android.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.artificialss.showcase.android.ui.feature.home.HomeScreen
import com.artificialss.showcase.android.ui.feature.portfolio.PortfolioScreen
import com.artificialss.showcase.android.ui.feature.splash.SplashScreen

@Composable
fun ShowcaseNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = AppRoute.Splash,
        modifier = modifier,
    ) {
        composable<AppRoute.Splash> {
            SplashScreen(
                onNavigateToHome = {
                    navController.navigate(AppRoute.Home) {
                        popUpTo(AppRoute.Splash) { inclusive = true }
                    }
                },
            )
        }
        composable<AppRoute.Home> { HomeScreen() }
        composable<AppRoute.Portfolio> { PortfolioScreen() }
    }
}
