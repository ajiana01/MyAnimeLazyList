package com.ajiananta.submisijetpackcompose.ui.nav

sealed class Screen(val route: String) {
    object HomeMenu : Screen("home")
    object FavoriteScreen : Screen("favorite")
    object Profile : Screen("profile")
    object Detail : Screen("home/{id}") {
        fun createRoute(id: Long) = "home/$id"
    }
}