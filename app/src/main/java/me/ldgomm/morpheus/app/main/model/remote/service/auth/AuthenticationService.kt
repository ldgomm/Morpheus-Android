package me.ldgomm.morpheus.app.main.model.remote.service.auth

import me.ldgomm.morpheus.app.main.model.remote.entity.auth.AuthenticationApiRequest
import me.ldgomm.morpheus.app.main.model.remote.entity.auth.AuthenticationApiResponse
import javax.inject.Inject

class AuthenticationService @Inject constructor(private val authenticationServiceableApi: AuthenticationServiceableApi) :
        AuthenticationServiceable {

    override suspend fun verifyGoogleIdToken(request: AuthenticationApiRequest): AuthenticationApiResponse {
        return try {
            authenticationServiceableApi.verifyGoogleIdToken(request)
        } catch (e: Exception) {
            AuthenticationApiResponse(success = false, error = e)
        }
    }
}