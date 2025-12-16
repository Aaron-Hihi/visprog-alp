package com.aaron.walkcore.model.user

import com.aaron.walkcore.model.enums.Gender

data class UserProfileModel(
    val username: String,
    val profilePictureUrl: String?,
    val email: String,
    val height: Double? = null,
    val weight: Double? = null,
    val gender: Gender? = null,
    val dateOfBirth: String? = null
)