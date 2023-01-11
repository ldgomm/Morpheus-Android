package me.ldgomm.morpheus.app.main.model.remote.service.auth

import me.ldgomm.morpheus.app.main.model.remote.entity.auth.AuthenticationApiRequest
import me.ldgomm.morpheus.app.main.model.remote.entity.auth.AuthenticationApiResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationServiceableApi {

    @POST(value = "/google_token_verification")
    suspend fun verifyGoogleIdToken(@Body request: AuthenticationApiRequest): AuthenticationApiResponse
}