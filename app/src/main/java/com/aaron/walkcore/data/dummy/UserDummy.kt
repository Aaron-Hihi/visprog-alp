package com.aaron.walkcore.data.dummy

import com.aaron.walkcore.model.enums.Gender
import com.aaron.walkcore.model.user.UserDetailsModel
import com.aaron.walkcore.model.user.UserSimpleModel

object UserDummy {
    val UserCompleteActive = UserDetailsModel(
        id = "user_001",
        username = "alex_walker_pro",
        email = "alex.pro@example.com",
        profilePictureUrl = "https://picsum.photos/400/400?random=101",

        dateOfBirth = "1995-05-15",
        gender = Gender.MALE,
        height = 175.5,
        weight = 72.8,

        currency = 5400,
        totalSteps = "1,540,900", // Format String untuk kemudahan tampilan (dengan koma)
        totalDistance = 1232.7, // dalam km
        totalActiveTime = 890000, // dalam detik
        totalSessionTime = 560000, // dalam detik
        totalSessionCount = 154,
        totalCaloriesBurned = 78000,
        longestStreak = 45,

        createdAt = "2024-01-10T10:00:00Z",
        updatedAt = "2025-12-16T12:30:00Z"
    )


    val UserNewMinimal = UserDetailsModel(
        id = "user_002",
        username = "rara_newbie",
        email = "rara@example.com",
        profilePictureUrl = "https://picsum.photos/400/400?random=102",

        dateOfBirth = null, // Data opsional kosong
        gender = Gender.FEMALE,
        height = 162.0,
        weight = null, // Data opsional kosong

        currency = 50,
        totalSteps = "8,450",
        totalDistance = 5.8,
        totalActiveTime = 3600,
        totalSessionTime = 1800,
        totalSessionCount = 3,
        totalCaloriesBurned = 450,
        longestStreak = 1,

        createdAt = "2025-12-15T08:00:00Z",
        updatedAt = "2025-12-16T10:30:00Z"
    )


    val UserDataMissing = UserDetailsModel(
        id = "user_003",
        username = "ghost_user",
        email = "ghost@example.com",
        profilePictureUrl = null, // Tidak ada foto profil

        dateOfBirth = null,
        gender = null, // Gender tidak diketahui
        height = null,
        weight = null,

        currency = 0,
        totalSteps = "0",
        totalDistance = 0.0,
        totalActiveTime = 0,
        totalSessionTime = 0,
        totalSessionCount = 0,
        totalCaloriesBurned = 0,
        longestStreak = 0,

        createdAt = "2025-12-01T00:00:00Z",
        updatedAt = "2025-12-01T00:00:00Z"
    )

    // USER SIMPLE
    val UserDataSimpleDummy = UserSimpleModel(
        id = "user_001",
        username = "alex_walker_pro",
        stepsWalked = 1234,
        profilePictureUrl = "https://picsum.photos/400/400?random=101",
    )

    // Dummy user with high step count, representing a pro athlete.
    val UserSimplePro = UserSimpleModel(
        id = "user_002",
        username = "Alex_Walker_Pro",
        stepsWalked = 18500,
        profilePictureUrl = "https://picsum.photos/400/400?random=101",
    )

    // Dummy user with moderate steps, showing typical daily activity.
    val UserSimpleRegular = UserSimpleModel(
        id = "user_003",
        username = "Rara_Anggraini",
        stepsWalked = 8750,
        profilePictureUrl = "https://picsum.photos/400/400?random=102",
    )

    // Dummy user with low steps, representing a beginner or inactive day.
    val UserSimpleBeginner = UserSimpleModel(
        id = "user_004",
        username = "Tito_Pratama_ID",
        stepsWalked = 3120,
        profilePictureUrl = "https://picsum.photos/400/400?random=103",
    )

    val AllSimpleUsers = listOf(
        UserDataSimpleDummy,
        UserSimplePro,
        UserSimpleRegular,
        UserSimpleBeginner,
    )
}