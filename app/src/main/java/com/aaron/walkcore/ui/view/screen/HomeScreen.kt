package com.aaron.walkcore.ui.view.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.aaron.walkcore.data.dummy.SessionOverviewDummy
import com.aaron.walkcore.data.dummy.UserDummy
import com.aaron.walkcore.route.AppView
import com.aaron.walkcore.ui.theme.Blue
import com.aaron.walkcore.ui.theme.WalkcoreTheme
import com.aaron.walkcore.ui.theme.YellowToBlue
import com.aaron.walkcore.ui.theme.YellowToGreen
import com.aaron.walkcore.ui.view.component.ButtonComponent
import com.aaron.walkcore.ui.view.component.LabelValueComponent
import com.aaron.walkcore.ui.view.component.session.ListSessionOverviewComponent
import com.aaron.walkcore.ui.view.component.session.OngoingSessionOverviewComponent
import com.aaron.walkcore.ui.view.component.user.ListUserSimpleComponent


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Welcome message header
        Text(
            text = "Hello, Jermy!",
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )

        // Activity metrics row
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LabelValueComponent(
                label = "Steps\nToday",
                value = "389/2000",
                brush = YellowToGreen,
                modifier = Modifier.weight(1f)
            )

            LabelValueComponent(
                label = "Steps\nCalories Burned",
                value = "125 kcal",
                brush = YellowToBlue,
                modifier = Modifier.weight(1f)
            )
        }

        // Active session highlight
        OngoingSessionOverviewComponent(
            sessionOverview = SessionOverviewDummy.SessionDummyFull,
            onCardClick = {
                val id = SessionOverviewDummy.SessionDummyFull.id
                if (id.isNotEmpty()) {
                    navController.navigate("${AppView.SESSION_DETAILS.name}/$id")
                }
            },
            onButtonClick = { }
        )

        // Upcoming user events list
        ListSessionOverviewComponent(
            title = "Upcoming Events",
            sessionsList = SessionOverviewDummy.allSessions,
            onCardClick = { session ->
                if (session.id.isNotEmpty()) {
                    navController.navigate("${AppView.SESSION_DETAILS.name}/${session.id}")
                }
            },
            button = {
                ButtonComponent(
                    label = "See Schedule",
                    onClick = { },
                    color = Blue
                )
            }
        )

        // Friends list display
        ListUserSimpleComponent(
            title = "Your Friends",
            button = {
                ButtonComponent(
                    label = "See More",
                    onClick = { },
                    color = Blue
                )
            },
            userSimpleModels = UserDummy.AllSimpleUsers
        )

        // Global event exploration
        ListSessionOverviewComponent(
            title = "Explore Events",
            sessionsList = SessionOverviewDummy.allSessions,
            onCardClick = { session ->
                if (session.id.isNotEmpty()) {
                    navController.navigate("${AppView.SESSION_DETAILS.name}/${session.id}")
                }
            },
            button = {
                ButtonComponent(
                    label = "Explore More",
                    onClick = { },
                    color = Blue
                )
            }
        )

        // Friend activity feed
        ListSessionOverviewComponent(
            title = "From your Friends",
            sessionsList = SessionOverviewDummy.allSessions,
            onCardClick = { session ->
                if (session.id.isNotEmpty()) {
                    navController.navigate("${AppView.SESSION_DETAILS.name}/${session.id}")
                }
            },
            button = {
                ButtonComponent(
                    label = "Explore More",
                    onClick = {},
                    color = Blue
                )
            }
        )

        // Bottom spacing for scroll clarity
        Spacer(modifier = Modifier.height(24.dp))
    }
}

// Component preview configuration
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    WalkcoreTheme {
        HomeScreen(navController = rememberNavController())
    }
}