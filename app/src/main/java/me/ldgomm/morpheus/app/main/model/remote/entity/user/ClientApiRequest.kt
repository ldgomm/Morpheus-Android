package me.ldgomm.morpheus.app.main.model.remote.entity.user

import kotlinx.serialization.Serializable

//
//  UserApiRequest.kt
//  Morpheus
//
//  Created by José Ruiz on 11/1/23.
//

@Serializable
data class ClientApiRequest(val client: Client? = null)