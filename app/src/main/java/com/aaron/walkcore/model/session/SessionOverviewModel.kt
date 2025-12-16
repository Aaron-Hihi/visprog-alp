package com.aaron.walkcore.model.session

data class SessionOverviewModel(
    val id: String,
    val title: String,
    val creatorUsername: String,
    val description: String,
    val dateTimeRange: String,
    val imageUrl: String? = null,
    val locationName: String? = null
)