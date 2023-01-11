package me.ldgomm.morpheus.app.main.viewmodel.client

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.ldgomm.morpheus.app.main.model.remote.entity.user.ClientApiResponse
import me.ldgomm.morpheus.app.main.model.remote.service.HttpRequestState
import me.ldgomm.morpheus.app.main.model.remote.service.user.ClientServiceable
import javax.inject.Inject

@HiltViewModel
class ClientViewModel @Inject constructor(private val clientServiceable: ClientServiceable) :
        ViewModel() {

    private val _clientApiResponse: MutableState<HttpRequestState<ClientApiResponse>> =
        mutableStateOf(HttpRequestState.Idle)
    val clientApiResponse: State<HttpRequestState<ClientApiResponse>> = _clientApiResponse

    init {
        getClient()
    }

    private fun getClient() {
        _clientApiResponse.value = HttpRequestState.Loading
        try {
            viewModelScope.launch(Dispatchers.IO) {
                val response = clientServiceable.getClient()
                _clientApiResponse.value = HttpRequestState.Success(response)
            }
        } catch (e: Exception) {
            _clientApiResponse.value = HttpRequestState.Error(e)
        }
    }
}