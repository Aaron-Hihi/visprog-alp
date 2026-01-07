package com.aaron.walkcore.data.service

import com.aaron.walkcore.data.dto.ActiveSessionDTO
import com.aaron.walkcore.data.dto.AuthResponse
import com.aaron.walkcore.data.dto.CreateSessionRequest
import com.aaron.walkcore.data.dto.HomeData
import com.aaron.walkcore.data.dto.LeaderboardEntryDTO
import com.aaron.walkcore.data.dto.LoginRequest
import com.aaron.walkcore.data.dto.RegisterRequest
import com.aaron.walkcore.data.dto.SessionDTO
import com.aaron.walkcore.data.dto.WalkcoreResponse
import retrofit2.http.*

interface WalkcoreApiService {

    // === AUTH & USER ===
    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): AuthResponse

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): AuthResponse

    // Get profile and stats for Home Screen header
    @GET("users/me/overview")
    suspend fun getHomeOverview(): WalkcoreResponse<HomeData>

    // Get the only one ongoing session for the user
    @GET("users/me/sessions/active")
    suspend fun getActiveSession(): WalkcoreResponse<ActiveSessionDTO?>

    // Get simple list of friends
    @GET("friends")
    suspend fun getFriends(): FriendListResponse

    // === SESSIONS ===

    // List all sessions for exploration
    @GET("sessions")
    suspend fun getAllSessions(): WalkcoreResponse<List<SessionDTO>>

    // Get detail of a specific session
    @GET("sessions/{sessionId}")
    suspend fun getSessionDetail(
        @Path("sessionId") sessionId: String
    ): WalkcoreResponse<SessionDTO>

    // Get everyone who joined the session
    @GET("sessions/{sessionId}/participants")
    suspend fun getParticipants(
        @Path("sessionId") sessionId: String
    ): WalkcoreResponse<List<ParticipantDTO>>

    // Get session ranking
    @GET("sessions/{sessionId}/leaderboard")
    suspend fun getLeaderboard(
        @Path("sessionId") sessionId: String
    ): WalkcoreResponse<List<LeaderboardEntryDTO>>

    // Create a new session (Session Add)
    @POST("sessions")
    suspend fun createSession(
        @Body request: CreateSessionRequest
    ): WalkcoreResponse<SessionDTO>
}

// Special case for friends endpoint based on your JSON output
data class FriendListResponse(
    val friends: List<FriendSimpleDTO>
)

data class FriendSimpleDTO(
    val id: String,
    val username: String
)

data class ParticipantDTO(
    val userId: String,
    val username: String,
    val status: String,
    val isAdmin: Boolean
)