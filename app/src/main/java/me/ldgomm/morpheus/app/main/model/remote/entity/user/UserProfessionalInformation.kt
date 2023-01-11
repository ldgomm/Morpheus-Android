package me.ldgomm.morpheus.app.main.model.remote.entity.user

import kotlinx.serialization.Serializable
import me.ldgomm.morpheus.app.main.model.remote.entity.offer.Preparation

@Serializable
data class UserProfessionalInformation(var preparation: Preparation? = null,
                                       var wageAspiration: Int? = null,
                                       var category: String? = null,
                                       var location: String? = null)