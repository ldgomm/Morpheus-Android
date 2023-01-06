package me.ldgomm.morpheus.app.main.model.remote.service.auth.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthenticationDataStoreOperationsModule {

    @Singleton
    @Provides
    fun provideDataStorePreferences(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(produceFile = { context.preferencesDataStoreFile("app_preferences") })
    }

    @Singleton
    @Provides
    fun provideAuthenticationDataStoreOperable(dataStore: DataStore<Preferences>): AuthenticationDataStoreOperable {
        return AuthenticationDataStoreOperations(dataStore)
    }

    @Singleton
    @Provides
    fun provideAccessDataStoreOperable(authenticationDataStoreOperable: AuthenticationDataStoreOperable): AccessDataStoreOperable {
        return AccessDataStoreOperations(authenticationDataStoreOperable)
    }
}