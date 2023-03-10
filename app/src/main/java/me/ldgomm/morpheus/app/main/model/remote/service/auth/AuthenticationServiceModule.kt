package me.ldgomm.morpheus.app.main.model.remote.service.auth

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthenticationServiceModule {

    @Singleton
    @Provides
    fun provideAuthenticationService(retrofit: Retrofit): AuthenticationServiceableApi {
        return retrofit.create(AuthenticationServiceableApi::class.java)
    }
}