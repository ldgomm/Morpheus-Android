package me.ldgomm.morpheus.app.main.view.client

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import me.ldgomm.morpheus.app.main.model.remote.entity.user.Client
import me.ldgomm.morpheus.app.main.model.remote.entity.user.ClientApiResponse
import me.ldgomm.morpheus.app.main.model.remote.service.HttpRequestState
import me.ldgomm.morpheus.app.main.viewmodel.client.ClientViewModel

@Composable
fun ClientView(navHostController: NavHostController,
               clientViewModel: ClientViewModel = hiltViewModel()) {

    val clientApiResponse by clientViewModel.clientApiResponse

    when (clientApiResponse) {
        is HttpRequestState.Error -> {
            ErrorView()
        }
        is HttpRequestState.Success -> {
            SuccessView(clientApiResponse as HttpRequestState.Success<ClientApiResponse>)
        }
        else -> {
            Text(text = "Idle or Loading")
        }
    }
}

@Composable
fun ErrorView() {
    Text(text = "Error")
}

@Composable
fun SuccessView(clientApiResponse: HttpRequestState.Success<ClientApiResponse>,
                client: Client? = clientApiResponse.data.client) {
    client?.let { Text(text = it.name) }
}