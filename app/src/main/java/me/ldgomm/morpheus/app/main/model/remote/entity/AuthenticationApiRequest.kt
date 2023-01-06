package me.ldgomm.morpheus.app.main.model.remote.entity

import kotlinx.serialization.Serializable

@Serializable
data class AuthenticationApiRequest(val idToken: String, val prime: Int)
