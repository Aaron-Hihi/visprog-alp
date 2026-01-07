package com.aaron.walkcore.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aaron.walkcore.data.dummy.SessionDummy
import com.aaron.walkcore.ui.view.state.SessionDetailsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SessionDetailsViewModel : ViewModel() {
    /* ==============================
    ========= GETTER SETTER =========
    ============================== */
    // Screen state (empty, found, not found)
    private val _screenState = MutableStateFlow<SessionDetailsState>(SessionDetailsState.Loading)
    val screenState: StateFlow<SessionDetailsState> = _screenState

    // Button

    /* ==============================
    ============== INIT =============
    ============================== */


    /* ==============================
    =========== FUNCTIONS ===========
    ============================== */

    // Fetch session details
    fun fetchSessionDetails(sessionId: String?) {
        if (sessionId.isNullOrEmpty()) {
            _screenState.value = SessionDetailsState.Error("Session not found")
            return
        }

        viewModelScope.launch {
            _screenState.value = SessionDetailsState.Loading

            try {
                val result = SessionDummy.allSessionDetails.find { it.id == sessionId }

                if (result != null) {
                    _screenState.value = SessionDetailsState.Success(result)
                } else {
                    _screenState.value = SessionDetailsState.Error("Session not found")
                }
            } catch (e: Exception) {
                _screenState.value = SessionDetailsState.Error(e.message ?: "An error occurerd")
            }
        }
    }
}