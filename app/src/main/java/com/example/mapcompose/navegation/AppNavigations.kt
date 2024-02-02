package com.example.mapcompose.navegation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mapcompose.LoginViewModel
import com.example.mapcompose.screens.AppScreen
import com.example.mapcompose.screens.LoginScreen
import com.example.mapcompose.screens.SearchScreen

@Composable
fun AppNavigations(){
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = NavigationScreens.LoginScreen.route, builder = {
        composable(route = NavigationScreens.LoginScreen.route){
            LoginScreen(LoginViewModel(), navController)
        }
        composable(route = NavigationScreens.AppScreen.route){
            AppScreen(navController)
        }
        composable(route = NavigationScreens.SearchScreen.route){
            SearchScreen()
        }
    })
}