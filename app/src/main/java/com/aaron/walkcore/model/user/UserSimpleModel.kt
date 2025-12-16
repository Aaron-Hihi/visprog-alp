package com.aaron.walkcore.model.user

import com.aaron.walkcore.model.enums.Gender

data class UserSimpleModel(
    val username: String,
    val profilePictureUrl: String?,
    val stepsWalked: Int,
)