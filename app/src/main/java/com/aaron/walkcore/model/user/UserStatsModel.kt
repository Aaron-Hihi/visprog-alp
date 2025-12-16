package com.aaron.walkcore.model.user

data class UserStatsModel(
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