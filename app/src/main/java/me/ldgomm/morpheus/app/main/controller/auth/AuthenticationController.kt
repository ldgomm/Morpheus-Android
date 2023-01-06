package me.ldgomm.morpheus.app.main.controller.auth

import android.app.Activity
import android.content.ContentValues.TAG
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import me.ldgomm.morpheus.app.util.constant.Constants.client_id

@Composable
fun StartActivityForResult(key: Any,
                           onGoogleIdTokenReceived: (googleIdToken: String) -> Unit,
                           onDialogDismissed: (Boolean) -> Unit,
                           launcherForActivityResult: (ManagedActivityResultLauncher<IntentSenderRequest, ActivityResult>) -> Unit) {
    val activity = LocalContext.current as Activity
    val activityLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartIntentSenderForResult()) {
            try {
                if (it.resultCode == Activity.RESULT_OK) {
                    val oneTapClient = Identity.getSignInClient(activity)
                    val credentials = oneTapClient.getSignInCredentialFromIntent(it.data)
                    val googleIdToken = credentials.googleIdToken
                    if (googleIdToken != null) {
                        onGoogleIdTokenReceived(googleIdToken)
                    }
                } else {
                    Log.d(TAG, "StartActivityForResult: Black screen clicked")
                    onDialogDismissed(false)
                }
            } catch (e: ApiException) {
                Log.d(TAG, "StartActivityForResult: ")
                when (e.statusCode) {
                    CommonStatusCodes.CANCELED -> {
                        Log.d(TAG, "StartActivityForResult: One tap dialog cancelled")
                        onDialogDismissed(false)
                    }
                    CommonStatusCodes.NETWORK_ERROR -> {
                        Log.d(TAG, "StartActivityForResult: One tap network error")
                        onDialogDismissed(false)
                    }
                    else -> {
                        Log.d(TAG, "StartActivityForResult: ${e.message}")
                        onDialogDismissed(false)
                    }
                }
            }
        }
    LaunchedEffect(key1 = key, block = { launcherForActivityResult(activityLauncher) })
}

fun signIn(activity: Activity,
           intentSenderForResult: (IntentSenderRequest) -> Unit,
           accountNotFound: (Boolean) -> Unit) {
    val oneTapClient = Identity.getSignInClient(activity)
    val signInRequest = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                                            .setSupported(true).setServerClientId(client_id)
                                            .setFilterByAuthorizedAccounts(true).build())
        .setAutoSelectEnabled(true).build() //IMPORTANT*********************************************
    oneTapClient.beginSignIn(signInRequest).addOnSuccessListener {
        try {
            intentSenderForResult(IntentSenderRequest.Builder(it.pendingIntent.intentSender)
                                      .build())
        } catch (e: Exception) {
            Log.d(TAG, "signIn: Could not start one tap: ${e.message}")
        }
    }.addOnFailureListener {
        Log.d(TAG, "signIn: Signing up")
        signUp(activity, intentSenderForResult, accountNotFound)
    }
}

fun signUp(activity: Activity,
           intentSenderForResult: (IntentSenderRequest) -> Unit,
           accountNotFound: (Boolean) -> Unit) {
    val oneTapClient = Identity.getSignInClient(activity)
    val signInRequest = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                                            .setSupported(true).setServerClientId(client_id)
                                            .setFilterByAuthorizedAccounts(false).build()).build()
    oneTapClient.beginSignIn(signInRequest).addOnSuccessListener {
        try {
            intentSenderForResult(IntentSenderRequest.Builder(it.pendingIntent.intentSender)
                                      .build())
        } catch (e: Exception) {
            Log.d(TAG, "signIn: Could not start one tap: ${e.message}")
        }
    }.addOnFailureListener {
        Log.d(TAG, "signIn: Signing up")
        accountNotFound(false)
    }
}