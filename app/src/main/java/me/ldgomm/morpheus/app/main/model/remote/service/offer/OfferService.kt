package me.ldgomm.morpheus.app.main.model.remote.service.offer

//
//  OfferService.kt
//  Morpheus
//
//  Created by José Ruiz on 11/1/23.
//

import me.ldgomm.morpheus.app.main.model.remote.entity.offer.OfferApiResponse
import javax.inject.Inject

class OfferService @Inject constructor(private val offerServiceableApi: OfferServiceableApi) :
        OfferServiceable {
    override suspend fun getOffers(): OfferApiResponse {
        return try {
            offerServiceableApi.getOffers()
        } catch (e: Exception) {
            OfferApiResponse(success = false, error = e)
        }
    }
}