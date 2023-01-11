package me.ldgomm.morpheus.app.main.model.remote.service.user

import me.ldgomm.morpheus.app.main.model.remote.entity.user.ClientApiResponse
import javax.inject.Inject

class ClientService @Inject constructor(private val clientServiceableApi: ClientServiceableApi) :
        ClientServiceable {
    override suspend fun getClient(): ClientApiResponse {
        return try {
            clientServiceableApi.getClient()
        } catch (e: Exception) {
            ClientApiResponse(success = false, error = e)
        }
    }
}