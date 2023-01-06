package me.ldgomm.morpheus.app.main.model.remote.entity

import kotlinx.serialization.Serializable

@Serializable
data class UserPartner(val idUser: String, val name: String, val email: String, val aud: String)
