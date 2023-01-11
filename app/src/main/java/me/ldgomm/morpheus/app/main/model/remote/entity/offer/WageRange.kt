package me.ldgomm.morpheus.app.main.model.remote.entity.offer

import kotlinx.serialization.Serializable

@Serializable
data class WageRange(var minimum: Int? = null, var maximum: Int? = null, var currency: Int = 0)
