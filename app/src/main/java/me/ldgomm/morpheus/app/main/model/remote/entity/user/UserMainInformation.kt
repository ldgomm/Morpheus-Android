package me.ldgomm.morpheus.app.main.model.remote.entity.user

import kotlinx.serialization.Serializable

@Serializable
data class UserMainInformation(var prefix: String? = null,
                               var suffix: String? = null,
                               var ci: String? = null,
                               var ruc: String? = null,
                               var about: String? = null,
                               var birthday: Long? = null)