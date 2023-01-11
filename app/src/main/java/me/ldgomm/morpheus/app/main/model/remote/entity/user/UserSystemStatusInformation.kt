package me.ldgomm.morpheus.app.main.model.remote.entity.user

import kotlinx.serialization.Serializable

@Serializable
data class UserSystemStatusInformation(val isValidated: Boolean = false,
                                       val isActive: Boolean = true,
                                       val isSuspended: Boolean = false,
                                       val isBlocked: Boolean = false)