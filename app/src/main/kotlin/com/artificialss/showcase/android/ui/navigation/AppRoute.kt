package com.artificialss.showcase.android.ui.navigation

import kotlinx.serialization.Serializable

sealed interface AppRoute {
    @Serializable
    data object Splash : AppRoute

    @Serializable
    data object Home : AppRoute

    @Serializable
    data object Portfolio : AppRoute
}
