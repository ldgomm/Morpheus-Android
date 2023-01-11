package me.ldgomm.morpheus.app.main.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.ldgomm.morpheus.app.main.view.auth.AuthenticationView
import me.ldgomm.morpheus.app.main.view.client.ClientView
import me.ldgomm.morpheus.app.main.view.offer.OffersView
import me.ldgomm.morpheus.app.main.viewmodel.auth.AuthenticationViewModel

@Composable
fun MainView(authenticationViewModel: AuthenticationViewModel = hiltViewModel()) {
    val navHostController: NavHostController = rememberNavController()

    val isAuthenticated by authenticationViewModel.isAuthenticatedState

    NavigationGraph(navHostController,
                    startDestination = if (isAuthenticated == "yes") View.ClientView.route else View.AuthenticationView.route,
                    authenticationViewModel)
}

sealed class View(val route: String) {
    object AuthenticationView : View("authentication_view")
    object OffersView : View("offers_view")
    object ClientView : View("client_view")
}

@Composable
fun NavigationGraph(navHostController: NavHostController,
                    startDestination: String,
                    authenticationViewModel: AuthenticationViewModel) {

    NavHost(navController = navHostController, startDestination) {

        composable(route = View.AuthenticationView.route) {
            AuthenticationView(navHostController, authenticationViewModel)
        }
        composable(route = View.OffersView.route) {
            OffersView(navHostController)
        }
        composable(route = View.ClientView.route) {
            ClientView(navHostController)
        }
    }
}