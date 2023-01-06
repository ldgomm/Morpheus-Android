package me.ldgomm.morpheus.app.main.model.remote.service.auth.datastore

import kotlinx.coroutines.flow.Flow

sealed interface AccessDataStoreOperable {
    suspend fun saveSignedInState(signIn: Boolean)
    fun readSignedInState(): Flow<Boolean>

    suspend fun saveAuthenticationState(isAuthenticated: Boolean)
    fun readAuthenticationState(): Flow<Boolean>
}