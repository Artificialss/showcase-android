package com.artificialss.showcase.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.artificialss.showcase.android.ui.navigation.AppRoute
import com.artificialss.showcase.android.ui.navigation.ShowcaseBottomNavBar
import com.artificialss.showcase.android.ui.navigation.ShowcaseNavHost
import com.artificialss.showcase.android.ui.theme.ShowcaseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShowcaseTheme {
                ShowcaseApp()
            }
        }
    }
}

@Composable
private fun ShowcaseApp() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val showBottomBar = backStackEntry?.destination?.hasRoute(AppRoute.Splash::class) != true

    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        bottomBar = { if (showBottomBar) ShowcaseBottomNavBar(navController) },
    ) {
        // Screens own their edge-to-edge backgrounds and apply their own inset
        // padding — the Scaffold's content padding is intentionally unused.
        ShowcaseNavHost(navController = navController, modifier = Modifier)
    }
}
