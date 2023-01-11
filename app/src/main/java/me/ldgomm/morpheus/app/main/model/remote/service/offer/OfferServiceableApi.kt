package me.ldgomm.morpheus.app.main.model.remote.service.offer

import me.ldgomm.morpheus.app.main.model.remote.entity.offer.OfferApiResponse
import retrofit2.http.GET

//
//  OfferServiceableApi.kt
//  Morpheus
//
//  Created by José Ruiz on 11/1/23.
//

interface OfferServiceableApi {

    @GET(value = "/offer")
    suspend fun getOffers(): OfferApiResponse
}