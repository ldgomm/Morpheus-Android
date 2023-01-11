package me.ldgomm.morpheus.app.main.model.remote.entity.offer

import kotlinx.serialization.Serializable
import me.ldgomm.morpheus.app.main.model.remote.entity.offer.Certification

@Serializable
data class Education(var area: String? = null,
                     var degree: String? = null,
                     var level: String? = null,
                     var specialization: String? = null,
                     var certifications: List<Certification>? = null,
                     var mandatory: Boolean = false)
