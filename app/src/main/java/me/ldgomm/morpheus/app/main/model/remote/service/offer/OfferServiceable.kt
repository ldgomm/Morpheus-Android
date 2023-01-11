package me.ldgomm.morpheus.app.main.model.remote.service.offer

import me.ldgomm.morpheus.app.main.model.remote.entity.offer.OfferApiResponse

//
//  OfferServiceable.kt
//  Morpheus
//
//  Created by José Ruiz on 11/1/23.
//

interface OfferServiceable {

    suspend fun getOffers(): OfferApiResponse
}