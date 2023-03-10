package me.ldgomm.morpheus.app.main.model.remote.entity.user

import kotlinx.serialization.Serializable

@Serializable
data class Partner(val idUser: String,
                   var name: String,
                   val email: String,
                   val aud: String,
                   var userPersonalInformation: UserMainInformation? = null,
                   var userProfessionalInformation: UserProfessionalInformation? = null,
                   var userSystemStatusInformation: UserSystemStatusInformation? = null,
                   val timestamp: Long = System.currentTimeMillis() / 1000)