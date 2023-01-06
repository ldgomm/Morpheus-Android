package me.ldgomm.morpheus.app.main.view.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.ldgomm.morpheus.app.main.view.auth.AuthenticationView
import me.ldgomm.morpheus.app.main.view.profile.ProfileView

@Composable
fun MainView() {
    val navHostController: NavHostController = rememberNavController()

    NavigationGraph(navHostController)
}

sealed class View(val route: String) {
    object AuthenticationView : View("authentication_view")
    object ProfileView : View("profile_view")
}

@Composable
fun NavigationGraph(navHostController: NavHostController) {

    NavHost(navController = navHostController, startDestination = View.AuthenticationView.route) {

        composable(route = View.AuthenticationView.route) {
            AuthenticationView(navHostController)
        }
        composable(route = View.ProfileView.route) {
            ProfileView(navHostController)
        }
    }
}