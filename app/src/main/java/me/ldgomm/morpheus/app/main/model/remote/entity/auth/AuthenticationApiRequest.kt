package me.ldgomm.morpheus.app.main.model.remote.entity.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthenticationApiRequest(val idToken: String, val prime: Int)
