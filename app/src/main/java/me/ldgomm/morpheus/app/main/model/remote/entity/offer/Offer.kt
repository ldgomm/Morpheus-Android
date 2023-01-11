package me.ldgomm.morpheus.app.main.model.remote.entity.offer

import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class Offer(val idOffer: String = UUID.randomUUID().toString(),
                 val title: String,
                 val area: String,
                 val schedule: String,
                 val modality: String,
                 val description: String,
                 val location: Location? = null,
                 val preparation: Preparation? = null,
                 val benefits: List<Benefit>? = null,
                 val wageRange: WageRange? = null,
                 val details: List<Detail>? = null,
                 val publisher: String? = null,
                 val publishedOn: Long? = null,
                 val consumers: List<String>? = null,
                 val timestamp: Long = System.currentTimeMillis() / 1000)