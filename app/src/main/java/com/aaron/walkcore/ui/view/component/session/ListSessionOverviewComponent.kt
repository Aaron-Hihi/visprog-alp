package com.aaron.walkcore.ui.view.component.session

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aaron.walkcore.data.dummy.SessionOverviewDummy
import com.aaron.walkcore.model.session.SessionOverviewModel
import com.aaron.walkcore.ui.theme.Blue
import com.aaron.walkcore.ui.theme.WalkcoreTheme
import com.aaron.walkcore.ui.view.component.ButtonComponent

@Composable
fun ListSessionOverviewComponent(
    modifier: Modifier = Modifier,
    title: String? = null,
    button: (@Composable () -> Unit)? = null,
    sessionsList: List<SessionOverviewModel>,
    onCardClick: (SessionOverviewModel) -> Unit = {}
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.Start
    ) {
        // Padded header section for title and action button
        if (title != null || button != null) {
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                if (title != null) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.headlineMedium,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .weight(1f, fill = false)
                            .padding(end = 8.dp)
                    )
                }

                if (button != null) {
                    button()
                }
            }
        }

        // Horizontal list with edge-to-edge scrolling via contentPadding
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(
                items = sessionsList,
                key = { session -> session.id }
            ) { session ->
                SessionOverviewRowItemComponent(
                    sessionOverview = session,
                    showDescription = false,
                    onCardClick = { onCardClick(session) }
                )
            }

            if (sessionsList.isEmpty()) {
                item {
                    Text(
                        text = "No sessions found",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ListSessionOverviewPreview() {
    WalkcoreTheme {
        ListSessionOverviewComponent(
            title = "Upcoming Events",
            button = {
                ButtonComponent(
                    label = "See schedule",
                    onClick = {},
                    color = Blue
                )
            },
            sessionsList = SessionOverviewDummy.allSessions
        )
    }
}