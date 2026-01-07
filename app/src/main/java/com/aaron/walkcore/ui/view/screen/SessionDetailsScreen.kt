package com.aaron.walkcore.ui.view.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.aaron.walkcore.R
import com.aaron.walkcore.model.session.SessionOverviewModel
import com.aaron.walkcore.ui.theme.BlueToGreen
import com.aaron.walkcore.ui.theme.Green
import com.aaron.walkcore.ui.theme.LightGrey
import com.aaron.walkcore.ui.view.component.ButtonComponent
import com.aaron.walkcore.ui.viewmodel.SessionDetailViewModel

@Composable
fun SessionDetailScreen(
    sessionId: String,
    navController: NavHostController,
    viewModel: SessionDetailViewModel = viewModel(factory = SessionDetailViewModel.Factory)
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(sessionId) {
        viewModel.getSessionDetail(sessionId)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (uiState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center), color = Green)
        } else if (uiState.error != null) {
            Text(text = uiState.error!!, modifier = Modifier.align(Alignment.Center))
        } else {
            uiState.sessionData?.let { data ->
                SessionDetailContent(sessionData = data, modifier = Modifier.padding(16.dp))
            }
        }
    }
}

@Composable
fun SessionDetailContent(
    modifier: Modifier = Modifier,
    sessionData: SessionOverviewModel
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Visual header section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.5f)
                .background(LightGrey),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = sessionData.imageUrl,
                contentDescription = "Session Image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                error = painterResource(id = R.drawable.image_placeholder)
            )
        }

        // Information text section
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = sessionData.title,
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                text = "Hosted by ${sessionData.creatorUsername}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = sessionData.description,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Primary user interaction
            ButtonComponent(
                label = "Join Session",
                onClick = { },
                brush = BlueToGreen,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}