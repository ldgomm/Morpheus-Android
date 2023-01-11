package me.ldgomm.morpheus.app.main.model.remote.service.user

//
//  UserServiceableApi.kt
//  Morpheus
//
//  Created by José Ruiz on 11/1/23.
//

import me.ldgomm.morpheus.app.main.model.remote.entity.user.ClientApiResponse
import retrofit2.http.GET

interface ClientServiceableApi {

    @GET(value = "/client")
    suspend fun getClient(): ClientApiResponse
}