package com.aaron.walkcore.ui.view.component.session

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.aaron.walkcore.ui.theme.LightGrey
import com.aaron.walkcore.ui.theme.WalkcoreTheme

@Composable
fun SessionOverviewRowItemComponent(
    modifier: Modifier = Modifier,
    sessionOverview: SessionOverviewModel,
    showDescription: Boolean = false,
    onCardClick: () -> Unit = {}
) {
    val itemWidth = 280.dp

    // Use Card as the main container for styling and defining the width
    Card(
        modifier = modifier.width(itemWidth),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(0.dp),
        onClick = onCardClick
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Image Container
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = sessionOverview.imageUrl,
                    contentDescription = "Session Image: ${sessionOverview.title}",
                    modifier = Modifier
                        .fillMaxSize()
                        .background(LightGrey),
                    contentScale = ContentScale.Crop,
                    error = painterResource(id = R.drawable.image_placeholder),
                )
            }

            // All text and infos
            Column(
                modifier = Modifier
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
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    // Creator username
                    Text(
                        text = "By ${sessionOverview.creatorUsername}",
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                // Date, time, location
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Date and time
                    Text (
                        text = sessionOverview.dateTimeRange,
                        modifier = Modifier.weight(1f).padding(end = 4.dp),
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Start
                    )

                    // Location
                    Text (
                        text = sessionOverview.locationName ?: "Remote",
                        modifier = Modifier.weight(1f).padding(start = 4.dp),
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.End
                    )
                }

                // Description
                if (showDescription) {
                    Text(
                        text = sessionOverview.description,
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SessionOverviewRowItemPreview() {
    WalkcoreTheme {
        SessionOverviewRowItemComponent(
            sessionOverview = SessionOverviewDummy.SessionDummyFull,
            showDescription = false,
            modifier = Modifier.padding(16.dp)
        )
    }
}