package com.aaron.walkcore.data.repository

import com.aaron.walkcore.data.dto.ActiveSessionDTO
import com.aaron.walkcore.data.dto.AuthResponse
import com.aaron.walkcore.data.dto.CreateSessionRequest
import com.aaron.walkcore.data.dto.HomeData
import com.aaron.walkcore.data.dto.LeaderboardEntryDTO
import com.aaron.walkcore.data.dto.LoginRequest
import com.aaron.walkcore.data.dto.RegisterRequest
import com.aaron.walkcore.data.dto.SessionDTO
import com.aaron.walkcore.data.service.WalkcoreApiService

// Abstract definition for data access
interface WalkcoreRepository {
    suspend fun getHomeOverview(): HomeData
    suspend fun getActiveSession(): ActiveSessionDTO?
    suspend fun getAllSessions(): List<SessionDTO>
    suspend fun getSessionDetail(id: String): SessionDTO
    suspend fun getLeaderboard(id: String): List<LeaderboardEntryDTO>
    suspend fun createSession(request: CreateSessionRequest): SessionDTO
    suspend fun register(request: RegisterRequest): AuthResponse
    suspend fun login(request: LoginRequest): AuthResponse
}

// Network implementation using Retrofit service
class NetworkWalkcoreRepository(
    private val apiService: WalkcoreApiService
) : WalkcoreRepository {
    override suspend fun register(request: RegisterRequest): AuthResponse {
        return apiService.register(request)
    }

    override suspend fun login(request: LoginRequest): AuthResponse {
        return apiService.login(request)
    }

    // Global user profile and statistics fetching
    override suspend fun getHomeOverview(): HomeData {
        return apiService.getHomeOverview().data
    }

    // Current active session retrieval
    override suspend fun getActiveSession(): ActiveSessionDTO? {
        return apiService.getActiveSession().data
    }

    // Collection of all sessions for discovery
    override suspend fun getAllSessions(): List<SessionDTO> {
        return apiService.getAllSessions().data
    }

    // Specific session metadata fetching
    override suspend fun getSessionDetail(id: String): SessionDTO {
        return apiService.getSessionDetail(id).data
    }

    // Ranking data for specific session
    override suspend fun getLeaderboard(id: String): List<LeaderboardEntryDTO> {
        return apiService.getLeaderboard(id).data
    }

    // New session persistence to backend
    override suspend fun createSession(request: CreateSessionRequest): SessionDTO {
        return apiService.createSession(request).data
    }
}