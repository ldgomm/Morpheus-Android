package me.ldgomm.morpheus.app.main.model.remote.entity.offer

import kotlinx.serialization.Serializable
import me.ldgomm.morpheus.app.main.model.remote.entity.offer.Education
import me.ldgomm.morpheus.app.main.model.remote.entity.offer.Experience
import me.ldgomm.morpheus.app.main.model.remote.entity.offer.Knowledge

@Serializable
data class Preparation(var education: List<Education>? = null,
                       var knowledge: List<Knowledge>? = null,
                       var experience: List<Experience>? = null)
