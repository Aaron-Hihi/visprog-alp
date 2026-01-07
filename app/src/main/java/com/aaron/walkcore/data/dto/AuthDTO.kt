package com.aaron.walkcore.data.dto

import com.google.gson.annotations.SerializedName

// Request body for user registration
data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String
)

// Standard wrapper for authentication responses
data class AuthResponse(
    val status: String,
    val message: String,
    val data: AuthData
)

// Contains token and user profile info upon success
data class AuthData(
    val token: String,
    val user: UserDTO
)

data class UserDTO(
    val id: String,
    val username: String,
    val email: String
)

data class LoginRequest(
    val email: String,
    val password: String
)