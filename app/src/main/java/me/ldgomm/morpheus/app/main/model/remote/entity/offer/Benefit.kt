package me.ldgomm.morpheus.app.main.model.remote.entity.offer

import kotlinx.serialization.Serializable

@Serializable
data class Benefit(var name: String? = null, var description: String? = null)
