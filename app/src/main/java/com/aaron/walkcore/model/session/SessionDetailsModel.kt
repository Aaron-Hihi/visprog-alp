package com.aaron.walkcore.model.session

import com.aaron.walkcore.model.enums.SessionMode
import com.aaron.walkcore.model.enums.SessionStatus
import com.aaron.walkcore.model.enums.SessionVisibility
import com.aaron.walkcore.model.user.UserSimpleModel

data class SessionDetailsModel(
    val id: String,
    val title: String,
    val creatorUsername: String,
    val description: String,
    val dateTimeRange: String,
    val imageUrl: String? = null,
    val locationName: String? = null,

    val mode: SessionMode,
    val status: SessionStatus,
    val visibility: SessionVisibility,

    val maxParticipants: Int,
    val currentParticipants: List<UserSimpleModel>,
    val stepTarget: Int,

    val createdAt: String,
    val updatedAt: String
)