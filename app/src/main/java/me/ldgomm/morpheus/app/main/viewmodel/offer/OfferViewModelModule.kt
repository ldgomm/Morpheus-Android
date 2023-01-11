package me.ldgomm.morpheus.app.main.viewmodel.offer

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.ldgomm.morpheus.app.main.model.remote.service.offer.OfferService
import me.ldgomm.morpheus.app.main.model.remote.service.offer.OfferServiceable
import me.ldgomm.morpheus.app.main.model.remote.service.offer.OfferServiceableApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OfferViewModelModule {
    @Singleton
    @Provides
    fun provideOfferService(offerServiceableApi: OfferServiceableApi): OfferServiceable {
        return OfferService(offerServiceableApi)
    }
}