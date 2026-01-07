package com.aaron.walkcore.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.aaron.walkcore.WalkcoreApplication
import com.aaron.walkcore.data.dto.SessionDTO
import com.aaron.walkcore.data.repository.WalkcoreRepository
import com.aaron.walkcore.model.session.SessionOverviewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

// UI state for managing session detail data and loading status
data class SessionDetailUiState(
    val isLoading: Boolean = false,
    val sessionData: SessionOverviewModel? = null,
    val error: String? = null
)

class SessionDetailViewModel(private val repository: WalkcoreRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(SessionDetailUiState())
    val uiState: StateFlow<SessionDetailUiState> = _uiState.asStateFlow()

    // Fetches session data from network and maps to UI model
    fun getSessionDetail(sessionId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                val dto = repository.getSessionDetail(sessionId)
                val model = dto.toUIModel()

                _uiState.update {
                    it.copy(sessionData = model, isLoading = false)
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.localizedMessage ?: "Failed to load session"
                    )
                }
            }
        }
    }

    // Transform raw DTO to presentation model
    private fun SessionDTO.toUIModel(): SessionOverviewModel {
        return SessionOverviewModel(
            id = this.id,
            title = this.title,
            creatorUsername = "Organizer",
            description = this.description,
            dateTimeRange = "${this.startTime} - ${this.endTime}",
            imageUrl = null,
            locationName = if (this.mode == "REMOTE") "Remote" else "On-site"
        )
    }

    // Dependency injection factory
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as WalkcoreApplication)
                SessionDetailViewModel(application.container.walkcoreRepository)
            }
        }
    }
}