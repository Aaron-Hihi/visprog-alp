package com.aaron.walkcore.ui.view.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.aaron.walkcore.ui.theme.Blue
import com.aaron.walkcore.ui.view.component.ButtonComponent
import com.aaron.walkcore.ui.viewmodel.SessionAddViewModel

@Composable
fun SessionAddScreen(
    navController: NavHostController,
    viewModel: SessionAddViewModel = viewModel(factory = SessionAddViewModel.Factory)
) {
    val uiState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    // Navigation logic on successful creation
    LaunchedEffect(uiState.isSuccess) {
        if (uiState.isSuccess) {
            navController.popBackStack()
        }
    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header
            Text(
                text = "Create New Session",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.fillMaxWidth()
            )

            // Input Fields
            OutlinedTextField(
                value = uiState.title,
                onValueChange = viewModel::onTitleChange,
                label = { Text("Session Title") },
                modifier = Modifier.fillMaxWidth(),
                isError = uiState.error != null && uiState.title.isBlank()
            )

            OutlinedTextField(
                value = uiState.description,
                onValueChange = viewModel::onDescriptionChange,
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                OutlinedTextField(
                    value = uiState.stepTarget,
                    onValueChange = viewModel::onStepTargetChange,
                    label = { Text("Step Target") },
                    modifier = Modifier.weight(1f)
                )
                OutlinedTextField(
                    value = uiState.maxParticipants,
                    onValueChange = viewModel::onMaxParticipantsChange,
                    label = { Text("Max Users") },
                    modifier = Modifier.weight(1f)
                )
            }

            // Error Message Display
            if (uiState.error != null) {
                Text(
                    text = uiState.error!!,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Form Action
            if (uiState.isSubmitting) {
                CircularProgressIndicator(color = Blue)
            } else {
                ButtonComponent(
                    label = "Launch Session",
                    onClick = { viewModel.createSession() },
                    color = Blue,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}