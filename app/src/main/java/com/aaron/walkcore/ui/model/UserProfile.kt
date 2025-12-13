package com.aaron.walkcore.ui.model

data class UserProfile(
    // BIO
    val gender: String = "",
    val heightCm: Int = 0,
    val weightKg: Int = 0,
    val birthday: String = "",

    // TOTAL ACTIVITY
    val stepsWalked: Int = 0,
    val distanceKm: Float = 0f,
    val activeTimeMin: Int = 0,
    val sessionTimeMin: Int = 0,
    val sessionCount: Int = 0,
    val caloriesBurned: Int = 0,
    val averagePace: Float = 0f,
    val streakDays: Int = 0
)
