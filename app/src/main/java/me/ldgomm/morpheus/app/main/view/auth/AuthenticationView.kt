package me.ldgomm.morpheus.app.main.view.auth

import android.app.Activity
import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.ContentAlpha
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import me.ldgomm.morpheus.R.drawable.google_logo
import me.ldgomm.morpheus.app.main.controller.auth.StartActivityForResult
import me.ldgomm.morpheus.app.main.controller.auth.signIn
import me.ldgomm.morpheus.app.main.model.remote.entity.AuthenticationApiRequest
import me.ldgomm.morpheus.app.main.model.remote.entity.AuthenticationApiResponse
import me.ldgomm.morpheus.app.main.model.remote.service.HttpRequestState
import me.ldgomm.morpheus.app.main.view.main.View
import me.ldgomm.morpheus.app.main.viewmodel.auth.AuthenticationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthenticationView(navHostController: NavHostController,
                       authenticationViewModel: AuthenticationViewModel = hiltViewModel()) {

    val signedInState by authenticationViewModel.signedInState
    //    val isAuthenticated by authenticationViewModel.isAuthenticatedState

    val successErrorMessageBarState by authenticationViewModel.successErrorMessageBarState
    val authenticationApiResponse by authenticationViewModel.authenticationApiResponse

    val activity = LocalContext.current as Activity

    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Sign in") })
    }) {
        Column(Modifier.padding(it),
               verticalArrangement = Arrangement.Center,
               horizontalAlignment = Alignment.CenterHorizontally) {
            Column(Modifier.weight(1f)) {
                SuccessErrorMessageBarView(successErrorMessageBarState = successErrorMessageBarState)
            }
            Column(modifier = Modifier
                .weight(9f)
                .fillMaxWidth(),
                   verticalArrangement = Arrangement.Center,
                   horizontalAlignment = Alignment.CenterHorizontally) {
                Image(modifier = Modifier
                    .padding(bottom = 20.dp)
                    .size(120.dp),
                      painter = painterResource(id = google_logo),
                      contentDescription = "Google Logo")
                Text(text = "Sign in with Google", fontWeight = FontWeight.Bold)
                Text(text = "Some test",
                     modifier = Modifier
                         .alpha(ContentAlpha.medium)
                         .padding(bottom = 40.dp, top = 4.dp),
                     textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.padding(30.dp))
                SignInWithGoogleButtonView(signedInState = signedInState,
                                           onSignInWithGoogleButtonClicked = {
                                               authenticationViewModel.saveSignInState(signedInValue = true)
                                           })
            }
        }
    }

    StartActivityForResult(key = signedInState, onGoogleIdTokenReceived = {
        Log.d(TAG, "AuthenticationView: $it")
        authenticationViewModel.verifyToken(AuthenticationApiRequest(idToken = it, prime = 2))
    }, onDialogDismissed = {
        authenticationViewModel.saveSignInState(it)
    }, launcherForActivityResult = { launcherForActivityResult ->
        if (signedInState) {
            signIn(activity = activity, intentSenderForResult = { intentSenderForResult ->
                launcherForActivityResult.launch(intentSenderForResult)
            }, accountNotFound = {
                authenticationViewModel.saveSignInState(it)
                authenticationViewModel.updateSuccessErrorMessageBarState()
            })
        }
    })

    LaunchedEffect(key1 = authenticationApiResponse, block = {
        when (authenticationApiResponse) {
            is HttpRequestState.Success -> {
                val response =
                    (authenticationApiResponse as HttpRequestState.Success<AuthenticationApiResponse>).data.success
                if (response) {
                    authenticationViewModel.saveAuthenticationState(true)
                    navigateToProfileView(navHostController)
                } else {
                    authenticationViewModel.saveSignInState(false)
                }
            }
            else -> {
                Log.d(TAG, "AuthenticationView: Something happened")
            }
        }
    })
}

private fun navigateToProfileView(navHostController: NavHostController) {
    navHostController.navigate(View.ProfileView.route) {
        popUpTo(View.AuthenticationView.route) {
            inclusive = true
        }
    }
}