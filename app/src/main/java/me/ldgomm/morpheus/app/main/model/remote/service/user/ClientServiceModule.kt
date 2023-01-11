package me.ldgomm.morpheus.app.main.model.remote.service.user

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ClientServiceModule {

    @Singleton
    @Provides
    fun provideClientService(retrofit: Retrofit): ClientServiceableApi {
        return retrofit.create(ClientServiceableApi::class.java)
    }
}