package com.aaron.walkcore.data.dto

// Session Detail
data class SessionDetailResponse(
    val data: SessionDTO
)

data class SessionDTO(
    val id: String,
    val title: String,
    val description: String,
    val creatorId: String,
    val mode: String,
    val status: String,
    val visibility: String,
    val maxParticipants: Int,
    val stepTarget: Int,
    val startTime: String,
    val endTime: String,
    val startLat: Double?,
    val startLong: Double?
)

// Leaderboard List
data class LeaderboardResponse(
    val data: List<LeaderboardEntryDTO>
)

data class LeaderboardEntryDTO(
    val rank: Int,
    val userId: String,
    val totalSteps: Int,
    val approxDistance: String,
    val caloriesBurned: Int,
    val user: UserNameDTO
)

data class UserNameDTO(
    val username: String
)


data class CreateSessionRequest(
    val title: String,
    val description: String,
    val mode: String,
    val visibility: String,
    val maxParticipants: Int,
    val stepTarget: Int,
    val startTime: String, // Format ISO: "2026-01-07T05:00:14.997Z"
    val endTime: String
)