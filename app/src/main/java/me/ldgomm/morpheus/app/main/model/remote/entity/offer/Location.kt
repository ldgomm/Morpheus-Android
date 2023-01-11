package me.ldgomm.morpheus.app.main.model.remote.entity.offer

import kotlinx.serialization.Serializable

@Serializable
data class Location(val latitude: Double? = null, val longitude: Double? = null)