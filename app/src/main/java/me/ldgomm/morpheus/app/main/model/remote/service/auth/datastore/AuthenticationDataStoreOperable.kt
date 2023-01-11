package me.ldgomm.morpheus.app.main.model.remote.service.auth.datastore

import kotlinx.coroutines.flow.Flow

sealed interface AuthenticationDataStoreOperable {
    suspend fun saveSignedInState(signIn: Boolean)
    fun readSignedInState(): Flow<Boolean>

    suspend fun saveAuthenticationState(isAuthenticated: String)
    fun readAuthenticationState(): Flow<String>
}