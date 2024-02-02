package com.example.mapcompose.navegation

sealed class NavigationScreens(val route: String) {
    object AppScreen: NavigationScreens("app_screen")
    object SearchScreen: NavigationScreens("search_screen")
    object LoginScreen: NavigationScreens("login_screen")
}