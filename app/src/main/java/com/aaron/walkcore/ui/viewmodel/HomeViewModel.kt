package com.aaron.walkcore.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.aaron.walkcore.WalkcoreApplication
import com.aaron.walkcore.data.dto.ActiveSessionDTO
import com.aaron.walkcore.data.dto.HomeData
import com.aaron.walkcore.data.repository.WalkcoreRepository
import com.aaron.walkcore.model.session.SessionOverviewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

// State management for Home Screen UI
data class HomeUiState(
    val isLoading: Boolean = false,
    val homeData: HomeData? = null,
    val activeSession: SessionOverviewModel? = null,
    val error: String? = null
)

class HomeViewModel(private val repository: WalkcoreRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        refreshHomeData()
    }

    // Main logic for fetching and managing home data
    fun refreshHomeData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                val overview = repository.getHomeOverview()
                val activeDto = repository.getActiveSession()

                val activeSessionModel = activeDto?.toUIModel()

                _uiState.update {
                    it.copy(
                        homeData = overview,
                        activeSession = activeSessionModel,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.localizedMessage ?: "Unknown Error Occurred"
                    )
                }
            }
        }
    }

    // Mapper to transform network DTO to UI model
    private fun ActiveSessionDTO.toUIModel(): SessionOverviewModel {
        return SessionOverviewModel(
            id = this.sessionId,
            title = this.title,
            creatorUsername = "You",
            description = "Current active session progress",
            dateTimeRange = "${this.startTime} - ${this.endTime}",
            imageUrl = null,
            locationName = "Remote Location"
        )
    }

    // Factory to provide the repository to the ViewModel
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as WalkcoreApplication)
                HomeViewModel(application.container.walkcoreRepository)
            }
        }
    }
}