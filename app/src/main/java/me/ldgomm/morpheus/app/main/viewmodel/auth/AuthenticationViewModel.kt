package me.ldgomm.morpheus.app.main.viewmodel.auth

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.ldgomm.morpheus.app.main.model.remote.entity.auth.AuthenticationApiRequest
import me.ldgomm.morpheus.app.main.model.remote.entity.auth.AuthenticationApiResponse
import me.ldgomm.morpheus.app.main.model.remote.service.HttpRequestState
import me.ldgomm.morpheus.app.main.model.remote.service.auth.AuthenticationServiceable
import me.ldgomm.morpheus.app.main.model.remote.service.auth.datastore.AccessDataStoreOperable
import me.ldgomm.morpheus.app.main.view.auth.SuccessErrorMessageBar
import me.ldgomm.morpheus.app.util.exception.GoogleAccountNotFoundException
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(private val accessDataStoreOperable: AccessDataStoreOperable,
                                                  private val authenticationServiceable: AuthenticationServiceable) :
        ViewModel() {
    private val _signedInState: MutableState<Boolean> = mutableStateOf(false)
    val signedInState: State<Boolean> = _signedInState

    private val _isAuthenticatedState: MutableState<String> = mutableStateOf("no")
    val isAuthenticatedState: State<String> = _isAuthenticatedState

    private val _successErrorMessageBarState: MutableState<SuccessErrorMessageBar> =
        mutableStateOf(SuccessErrorMessageBar())
    val successErrorMessageBarState: State<SuccessErrorMessageBar> = _successErrorMessageBarState

    private val _authenticationApiResponse: MutableState<HttpRequestState<AuthenticationApiResponse>> =
        mutableStateOf(HttpRequestState.Idle)
    val authenticationApiResponse: State<HttpRequestState<AuthenticationApiResponse>> =
        _authenticationApiResponse

    init {
        viewModelScope.launch {
            accessDataStoreOperable.readSignedInState().collect { _signedInState.value = it }
            accessDataStoreOperable.readAuthenticationState()
                .collect { _isAuthenticatedState.value = it }
        }
    }

    fun saveSignInState(signedInValue: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            accessDataStoreOperable.saveSignedInState(signedInValue)
        }
    }

    fun saveAuthenticationState(isAuthenticated: String) {
        viewModelScope.launch(Dispatchers.IO) {
            accessDataStoreOperable.saveAuthenticationState(isAuthenticated)
        }
    }

    fun updateSuccessErrorMessageBarState() {
        _successErrorMessageBarState.value =
            SuccessErrorMessageBar(error = GoogleAccountNotFoundException())
    }

    fun verifyToken(request: AuthenticationApiRequest) {
        _authenticationApiResponse.value = HttpRequestState.Loading
        try {
            viewModelScope.launch(Dispatchers.IO) {
                val response = authenticationServiceable.verifyGoogleIdToken(request)
                _authenticationApiResponse.value = HttpRequestState.Success(response)
                _successErrorMessageBarState.value = SuccessErrorMessageBar(response.message)
            }
        } catch (e: Exception) {
            Log.d(TAG, "verifyToken: Failed to verify token error: ${e.message}")
            _authenticationApiResponse.value = HttpRequestState.Error(e)
            _successErrorMessageBarState.value = SuccessErrorMessageBar(e.message)
        }
    }
}