package me.ldgomm.morpheus.app.main.model.remote.entity.offer

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class OfferApiResponse(var success: Boolean = false,
                            var message: String? = null,
                            var offer: Offer? = null,
                            var offers: List<Offer>? = null,
                            @Transient val error: Exception? = null)
