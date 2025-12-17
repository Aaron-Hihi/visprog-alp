package com.aaron.walkcore.ui.view.component.session

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.aaron.walkcore.R
import com.aaron.walkcore.data.dummy.SessionOverviewDummy
import com.aaron.walkcore.model.session.SessionOverviewModel
import com.aaron.walkcore.ui.theme.Green
import com.aaron.walkcore.ui.theme.WalkcoreTheme

@Composable
fun SessionOverviewComponent(
    modifier: Modifier = Modifier,
    showDescription: Boolean = true,
    sessionOverview: SessionOverviewModel,
    fixedHeight: Boolean = true
) {
    /* ==============================
    ========== VARIABLES ==========
    ============================== */
    var isLoadingImage by remember { mutableStateOf(true) }

    /* ==============================
    ========== UI LAYOUT ==========
    ============================== */
    // --- View Vars ---
    val fixedTextContentHeight = 150.dp


    // --- Layout ---

    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Image
        Box (
            modifier = Modifier
                .aspectRatio(1f),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = sessionOverview.imageUrl,
                contentDescription = "Session Image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                error = painterResource(id = R.drawable.image_placeholder),
                onLoading = { isLoadingImage = true },
                onSuccess = { isLoadingImage = false },
                onError = { isLoadingImage = false },
            )

            if (isLoadingImage && sessionOverview.imageUrl != null) {
                CircularProgressIndicator(
                    modifier = Modifier.size(40.dp),
                    strokeWidth = 3.dp,
                    color = Green
                )
            }
        }

        // All text and infos
        Column(
            modifier = if (fixedHeight) Modifier
                    .height(fixedTextContentHeight)
                    .fillMaxWidth()
                else Modifier
                    .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Title and creator username
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // Title
                Text(
                    text = sessionOverview.title,
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center
                )

                // Creator username
                Text(
                    text = "By ${sessionOverview.creatorUsername}",
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
                    text = sessionOverview.dateTimeRange,
                    modifier = Modifier.weight(1f).padding(end = 4.dp),
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start
                )

                // Location
                Text (
                    text = sessionOverview.locationName ?: "Remote",
                    modifier = Modifier.weight(1f).padding(start = 4.dp),
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.End
                )
            }

            // Description
            if (showDescription) {
                Text (
                    text = sessionOverview.description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 7,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SessionOverviewPreview() {
    WalkcoreTheme {
        SessionOverviewComponent(
            sessionOverview = SessionOverviewDummy.SessionDummyFull,
            showDescription = true,
            modifier = Modifier.padding(16.dp)
        )
    }
}