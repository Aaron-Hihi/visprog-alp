package com.aaron.walkcore.ui.view.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.aaron.walkcore.R
import com.aaron.walkcore.data.dummy.SessionDummy
import com.aaron.walkcore.model.session.SessionDetailsModel
import com.aaron.walkcore.ui.theme.BlueToGreen
import com.aaron.walkcore.ui.theme.Green
import com.aaron.walkcore.ui.theme.LightGrey
import com.aaron.walkcore.ui.theme.WalkcoreTheme
import com.aaron.walkcore.ui.view.component.ButtonComponent
import com.aaron.walkcore.ui.view.component.session.SessionDetailsMetadataComponent
import com.aaron.walkcore.ui.view.component.user.ListRowUserSimpleComponent

@Composable
fun SessionDetailsScreen(
    modifier: Modifier = Modifier,
    sessionDetailsModel: SessionDetailsModel
) {
    /* ==============================
    ========== VARIABLES ==========
    ============================== */
    var isLoadingImage by remember { mutableStateOf(true) }
    val scrollState = rememberScrollState()

    /* ==============================
    ========== UI LAYOUT ==========
    ============================== */
    // --- Layout ---
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Image
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = sessionDetailsModel.imageUrl,
                contentDescription = "Session Image",
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = LightGrey),
                contentScale = ContentScale.Crop,
                error = painterResource(id = R.drawable.image_placeholder),
                onLoading = { isLoadingImage = true },
                onSuccess = { isLoadingImage = false },
                onError = { isLoadingImage = false },
            )

            if (isLoadingImage && sessionDetailsModel.imageUrl != null) {
                CircularProgressIndicator(
                    modifier = Modifier.size(40.dp),
                    strokeWidth = 3.dp,
                    color = Green
                )
            }
        }

        // All text and infos
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Title and creator username
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // Title
                Text(
                    text = sessionDetailsModel.title,
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Center
                )

                // Creator username
                Text(
                    text = "By ${sessionDetailsModel.creatorUsername}",
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center
                )
            }

            // Date, time, location
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                // Date and time
                Text (
                    text = sessionDetailsModel.dateTimeRange,
                    modifier = Modifier.weight(1f).padding(end = 4.dp),
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Start
                )

                // Location
                Text (
                    text = sessionDetailsModel.locationName ?: "Remote",
                    modifier = Modifier.weight(1f).padding(start = 4.dp),
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.End
                )
            }

            // Description
            Text (
                text = sessionDetailsModel.description,
                style = MaterialTheme.typography.bodyMedium,
            )

            // Button to join
            ButtonComponent(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp),
                onClick = { /*TODO*/ },
                label = "Join Now",
                brush = BlueToGreen,
            )

            // --- Metadata ---
            Spacer(
                modifier = Modifier
                    .height(4.dp)
            )

            // Details headline
            Text (
                text = "Details",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Session mode, solo or remote
                SessionDetailsMetadataComponent(
                    key = "Step target:",
                    value = sessionDetailsModel.stepTarget.toString()
                )

                // Session mode, solo or remote
                SessionDetailsMetadataComponent(
                    key = "Mode",
                    value = sessionDetailsModel.mode.displayText
                )

                // Session status, planned, ongoing, finished, cancelled
                SessionDetailsMetadataComponent(
                    key = "Status",
                    value = sessionDetailsModel.status.displayText
                )

                // Session visibility, public, friend only, invite only
                SessionDetailsMetadataComponent(
                    key = "Visibility",
                    value = sessionDetailsModel.visibility.displayText
                )

                // Session max participants
                SessionDetailsMetadataComponent(
                    key = "Max Participants",
                    value = sessionDetailsModel.maxParticipants.toString()
                )
            }



            // --- Participants ---
            Spacer(
                modifier = Modifier
                    .height(4.dp)
            )

            // Participants headline
            Text (
                text = "Participants",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .fillMaxWidth()
            )

            // Participants list
            ListRowUserSimpleComponent(
                userSimpleModels = sessionDetailsModel.currentParticipants,
                showSteps = false,
                maxUserShown = 3
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SessionDetailsPreview() {
    WalkcoreTheme {
        SessionDetailsScreen(
            sessionDetailsModel = SessionDummy.SessionDetailOngoingRemoteInvite,
            modifier = Modifier.padding(16.dp)
        )
    }
}