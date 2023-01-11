package me.ldgomm.morpheus.app.main.model.remote.service.offer

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OfferServiceModule {

    @Singleton
    @Provides
    fun provideOfferService(retrofit: Retrofit): OfferServiceableApi {
        return retrofit.create(OfferServiceableApi::class.java)
    }
}