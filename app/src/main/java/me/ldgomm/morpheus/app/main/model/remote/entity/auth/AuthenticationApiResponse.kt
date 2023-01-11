package me.ldgomm.morpheus.app.main.model.remote.entity.auth

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import me.ldgomm.morpheus.app.main.model.remote.entity.user.Client
import me.ldgomm.morpheus.app.main.model.remote.entity.user.Partner

@Serializable
data class AuthenticationApiResponse(val success: Boolean,
                                     val message: String? = null,
                                     val client: Client? = null,
                                     val partner: Partner? = null,
                                     @Transient val error: Exception? = null)
