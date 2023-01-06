package me.ldgomm.morpheus.app.main.model.remote.entity

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class AuthenticationApiResponse(val success: Boolean,
                                     val message: String? = null,
                                     val userClient: UserClient? = null,
                                     val userPartner: UserPartner? = null,
                                     @Transient val error: Exception? = null)
