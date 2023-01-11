package me.ldgomm.morpheus.app.main.model.remote.service.user

import me.ldgomm.morpheus.app.main.model.remote.entity.user.ClientApiResponse

//
//  UserServiceableApi.kt
//  Morpheus
//
//  Created by José Ruiz on 11/1/23.
//

interface ClientServiceable {
    suspend fun getClient(): ClientApiResponse
}
