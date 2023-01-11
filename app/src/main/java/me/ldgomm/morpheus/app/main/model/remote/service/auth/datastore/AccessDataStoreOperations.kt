package me.ldgomm.morpheus.app.main.model.remote.service.auth.datastore

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AccessDataStoreOperations @Inject constructor(private var dataStoreOperable: AuthenticationDataStoreOperable) :
        AccessDataStoreOperable {
    override suspend fun saveSignedInState(signIn: Boolean) {
        dataStoreOperable.saveSignedInState(signIn)
    }

    override fun readSignedInState(): Flow<Boolean> {
        return dataStoreOperable.readSignedInState()
    }

    override suspend fun saveAuthenticationState(isAuthenticated: String) {
        dataStoreOperable.saveAuthenticationState(isAuthenticated)
    }

    override fun readAuthenticationState(): Flow<String> {
        return dataStoreOperable.readAuthenticationState()
    }
}