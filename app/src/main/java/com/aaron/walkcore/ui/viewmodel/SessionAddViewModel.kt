package com.aaron.walkcore.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.aaron.walkcore.WalkcoreApplication
import com.aaron.walkcore.data.dto.CreateSessionRequest
import com.aaron.walkcore.data.repository.WalkcoreRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

// Form state management
data class SessionAddUiState(
    val title: String = "",
    val description: String = "",
    val stepTarget: String = "5000",
    val maxParticipants: String = "10",
    val mode: String = "REMOTE",
    val startTime: String = "2026-01-07T12:00:00Z",
    val endTime: String = "2026-01-07T14:00:00Z",
    val isSubmitting: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null
)

class SessionAddViewModel(private val repository: WalkcoreRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(SessionAddUiState())
    val uiState: StateFlow<SessionAddUiState> = _uiState.asStateFlow()

    // Form input handlers
    fun onTitleChange(value: String) = _uiState.update { it.copy(title = value) }
    fun onDescriptionChange(value: String) = _uiState.update { it.copy(description = value) }
    fun onStepTargetChange(value: String) = _uiState.update { it.copy(stepTarget = value) }
    fun onMaxParticipantsChange(value: String) = _uiState.update { it.copy(maxParticipants = value) }
    fun onModeChange(value: String) = _uiState.update { it.copy(mode = value) }

    // Session submission logic
    fun createSession() {
        val currentState = _uiState.value

        if (currentState.title.isBlank()) {
            _uiState.update { it.copy(error = "Title is required") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isSubmitting = true, error = null) }
            try {
                val request = CreateSessionRequest(
                    title = currentState.title,
                    description = currentState.description,
                    mode = currentState.mode,
                    visibility = "PUBLIC",
                    maxParticipants = currentState.maxParticipants.toIntOrNull() ?: 10,
                    stepTarget = currentState.stepTarget.toIntOrNull() ?: 5000,
                    startTime = currentState.startTime,
                    endTime = currentState.endTime
                )

                repository.createSession(request)
                _uiState.update { it.copy(isSubmitting = false, isSuccess = true) }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isSubmitting = false,
                        error = e.localizedMessage ?: "Failed to create session"
                    )
                }
            }
        }
    }

    // Factory for manual dependency injection
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as WalkcoreApplication)
                SessionAddViewModel(application.container.walkcoreRepository)
            }
        }
    }
}