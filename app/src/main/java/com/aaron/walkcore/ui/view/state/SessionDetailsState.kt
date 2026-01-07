package com.aaron.walkcore.ui.view.state

import com.aaron.walkcore.model.session.SessionDetailsModel

sealed class SessionDetailsState {
    object Loading : SessionDetailsState()
    data class Success(val data: SessionDetailsModel) : SessionDetailsState()
    data class Error(val message: String) : SessionDetailsState()
}