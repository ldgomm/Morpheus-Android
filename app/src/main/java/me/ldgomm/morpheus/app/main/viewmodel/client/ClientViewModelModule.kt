package me.ldgomm.morpheus.app.main.viewmodel.client

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.ldgomm.morpheus.app.main.model.remote.service.user.ClientService
import me.ldgomm.morpheus.app.main.model.remote.service.user.ClientServiceable
import me.ldgomm.morpheus.app.main.model.remote.service.user.ClientServiceableApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ClientViewModelModule {

    @Singleton
    @Provides
    fun provideUserService(clientServiceableApi: ClientServiceableApi): ClientServiceable {
        return ClientService(clientServiceableApi)
    }
}