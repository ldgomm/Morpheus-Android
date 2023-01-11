package me.ldgomm.morpheus.app.main.model.remote.service.auth.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class AuthenticationDataStoreOperations @Inject constructor(private val dataStore: DataStore<Preferences>) :
        AuthenticationDataStoreOperable {

    private object PreferencesKey {
        val signInKey = booleanPreferencesKey("signed_in_key")
        val authenticationKey = stringPreferencesKey("authentication_key")
    }

    override suspend fun saveSignedInState(signIn: Boolean) {
        dataStore.edit { it[PreferencesKey.signInKey] = signIn }
    }

    override fun readSignedInState(): Flow<Boolean> {
        return dataStore.data.catch {
            if (it is IOException) {
                emit(emptyPreferences())
            } else {
                throw it
            }
        }.map { it[PreferencesKey.signInKey] ?: false }
    }

    override suspend fun saveAuthenticationState(isAuthenticated: String) {
        dataStore.edit { it[PreferencesKey.authenticationKey] = isAuthenticated }
    }

    override fun readAuthenticationState(): Flow<String> {
        return dataStore.data.catch {
            if (it is IOException) {
                emit(emptyPreferences())
            } else {
                throw it
            }
        }.map { it[PreferencesKey.authenticationKey] ?: "no" }
    }
}