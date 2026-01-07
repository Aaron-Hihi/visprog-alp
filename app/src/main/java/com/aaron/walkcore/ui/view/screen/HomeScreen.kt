package com.aaron.walkcore.ui.view.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.aaron.walkcore.route.AppView
import com.aaron.walkcore.ui.theme.Blue
import com.aaron.walkcore.ui.theme.WalkcoreTheme
import com.aaron.walkcore.ui.theme.YellowToBlue
import com.aaron.walkcore.ui.theme.YellowToGreen
import com.aaron.walkcore.ui.view.component.ButtonComponent
import com.aaron.walkcore.ui.view.component.LabelValueComponent
import com.aaron.walkcore.ui.view.component.session.OngoingSessionOverviewComponent
import com.aaron.walkcore.ui.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory)
) {
    val uiState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    // Data Fetching Logic
    LaunchedEffect(Unit) {
        viewModel.refreshHomeData()
    }

    // Main UI State Switcher
    Box(modifier = modifier.fillMaxSize()) {
        if (uiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = Blue
            )
        } else if (uiState.error != null) {
            Text(
                text = uiState.error!!,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp),
                textAlign = TextAlign.Center
            )
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState),
                verticalArrangement = Arrangement.spacedBy(40.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Header Section
                Text(
                    text = "Hello, ${uiState.homeData?.profile?.username ?: "User"}!",
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )

                // User Performance Statistics
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LabelValueComponent(
                        label = "Steps\nToday",
                        value = "${uiState.homeData?.stats?.totalSteps ?: 0}/2000",
                        brush = YellowToGreen,
                        modifier = Modifier.weight(1f)
                    )

                    LabelValueComponent(
                        label = "Calories\nBurned",
                        value = "${uiState.homeData?.stats?.totalCaloriesBurned ?: 0} kcal",
                        brush = YellowToBlue,
                        modifier = Modifier.weight(1f)
                    )
                }

                // Active Participation Section
                uiState.activeSession?.let { sessionModel ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        // Component Title
                        Text(
                            text = "Current Activity",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.fillMaxWidth()
                        )

                        // Component just takes the model provided by ViewModel
                        OngoingSessionOverviewComponent(
                            sessionOverview = sessionModel,
                            onCardClick = {
                                navController.navigate("${AppView.SESSION_DETAILS.name}/${sessionModel.id}")
                            },
                            onButtonClick = {
                                navController.navigate("${AppView.SESSION_DETAILS.name}/${sessionModel.id}")
                            }
                        )
                    }
                }

                // Call to Action Section
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ButtonComponent(
                        label = "Create New Session",
                        onClick = {
                            // Navigation to be implemented in AppRouting
                        },
                        color = Blue
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    WalkcoreTheme {
        HomeScreen(navController = rememberNavController())
    }
}