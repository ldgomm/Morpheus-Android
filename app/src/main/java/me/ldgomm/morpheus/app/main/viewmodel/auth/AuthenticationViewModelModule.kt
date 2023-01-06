package me.ldgomm.morpheus.app.main.viewmodel.auth

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.ldgomm.morpheus.app.main.model.remote.service.auth.AuthenticationService
import me.ldgomm.morpheus.app.main.model.remote.service.auth.AuthenticationServiceable
import me.ldgomm.morpheus.app.main.model.remote.service.auth.AuthenticationServiceableApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthenticationViewModelModule {

    @Singleton
    @Provides
    fun provideAuthenticationService(authenticationServiceableApi: AuthenticationServiceableApi): AuthenticationServiceable {
        return AuthenticationService(authenticationServiceableApi)
    }
}