package me.ldgomm.morpheus.app.main.model.remote.entity.offer

import kotlinx.serialization.Serializable

@Serializable
data class Experience(var years: Int = 0, val jobs: List<Job>? = null, var mandatory: Boolean = false)
