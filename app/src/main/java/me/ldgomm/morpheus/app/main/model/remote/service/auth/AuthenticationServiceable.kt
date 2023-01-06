package me.ldgomm.morpheus.app.main.model.remote.service.auth

import me.ldgomm.morpheus.app.main.model.remote.entity.AuthenticationApiRequest
import me.ldgomm.morpheus.app.main.model.remote.entity.AuthenticationApiResponse
import retrofit2.http.Body
import retrofit2.http.POST

sealed interface AuthenticationServiceable {

    suspend fun verifyGoogleIdToken(request: AuthenticationApiRequest): AuthenticationApiResponse
}