package me.ldgomm.morpheus.app.main.view.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import me.ldgomm.morpheus.app.main.viewmodel.auth.AuthenticationViewModel

@Composable
fun ProfileView(navHostController: NavHostController,
                authenticationViewModel: AuthenticationViewModel = hiltViewModel()) {
    val signedInState by authenticationViewModel.signedInState

    Column {
        Text(text = "Is sign in state: $signedInState")

    }
}