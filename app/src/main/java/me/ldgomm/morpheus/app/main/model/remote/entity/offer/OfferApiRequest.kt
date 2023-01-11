package me.ldgomm.morpheus.app.main.model.remote.entity.offer

import kotlinx.serialization.Serializable
import me.ldgomm.morpheus.app.main.model.remote.entity.offer.Offer

@Serializable
data class OfferApiRequest(var offer: Offer)
