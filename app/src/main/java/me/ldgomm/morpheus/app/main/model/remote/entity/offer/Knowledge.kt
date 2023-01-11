package me.ldgomm.morpheus.app.main.model.remote.entity.offer

import kotlinx.serialization.Serializable

@Serializable
data class Knowledge(var name: String? = null, var description: String? = null, var mandatory: Boolean = false)