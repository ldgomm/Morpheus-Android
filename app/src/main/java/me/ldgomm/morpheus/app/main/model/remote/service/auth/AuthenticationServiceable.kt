package me.ldgomm.morpheus.app.main.model.remote.service.auth

import me.ldgomm.morpheus.app.main.model.remote.entity.auth.AuthenticationApiRequest
import me.ldgomm.morpheus.app.main.model.remote.entity.auth.AuthenticationApiResponse

sealed interface AuthenticationServiceable {

    suspend fun verifyGoogleIdToken(request: AuthenticationApiRequest): AuthenticationApiResponse
}