package com.aaron.walkcore.data.dto

// UI State untuk Home Screen
data class HomeOverviewResponse(
    val data: HomeData
)

data class HomeData(
    val profile: UserProfileDTO,
    val stats: UserStatsDTO
)

data class UserProfileDTO(
    val id: String,
    val username: String,
    val email: String,
    val gender: String
)

data class UserStatsDTO(
    val totalSteps: String,
    val totalDistance: String,
    val totalActiveTime: Int,
    val totalCaloriesBurned: Int,
    val longestStreak: Int
)

// DTO untuk Card "Running Event" (Active Session)
data class ActiveSessionResponse(
    val data: ActiveSessionDTO?
)

data class ActiveSessionDTO(
    val sessionId: String,
    val title: String,
    val status: String,
    val participantStatus: String,
    val startTime: String,
    val endTime: String,
    val totalSteps: Int
)