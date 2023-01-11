package me.ldgomm.morpheus.app.main.model.remote.entity.offer

import me.ldgomm.morpheus.app.main.model.remote.entity.offer.Offer

data class OfferApiResponse(var success: Boolean = false,
                            var message: String? = null,
                            var offer: Offer? = null,
                            var offers: List<Offer>? = null)
