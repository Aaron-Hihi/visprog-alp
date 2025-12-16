package com.aaron.walkcore.model.user

import com.aaron.walkcore.model.enums.Gender

data class UserOverviewModel(
    val id: String,
    val username: String,
    val email: String,
    val profilePictureUrl: String?,

    val dateOfBirth: String? = null,
    val gender: Gender? = null,
    val height: Double? = null,
    val weight: Double? = null,

    val currency: Int,
    val totalSteps: String,
    val totalDistance: Double,
    val totalActiveTime: Int,
    val totalSessionTime: Int,
    val totalSessionCount: Int,
    val totalCaloriesBurned: Int,
    val longestStreak: Int,

    val createdAt: String,
    val updatedAt: String
)