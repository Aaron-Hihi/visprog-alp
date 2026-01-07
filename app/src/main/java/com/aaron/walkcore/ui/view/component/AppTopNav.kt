package com.aaron.walkcore.ui.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aaron.walkcore.route.AppView
import com.aaron.walkcore.ui.theme.Black
import com.aaron.walkcore.ui.theme.BlueToGreen
import com.aaron.walkcore.ui.theme.WalkcoreTheme
import com.aaron.walkcore.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopNav(
    modifier: Modifier = Modifier,
    currentView: AppView?,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    currency: Long = 0,
    isColored: Boolean = false,
) {
    /* ==============================
    ========== VARIABLES ==========
    ============================== */
    // Mutable variables
//    val searchText by weatherViewModel.searchText
//    val screenState by weatherViewModel.screenState.collectAsState()


    /* ==============================
    ========== UI LAYOUT ==========
    ============================== */

    // --- Header Background Color ---
    val headerBrush: Brush = if (isColored) {
        BlueToGreen
    } else {
        SolidColor(Color.Transparent)
    }

    // --- Layout ---

    // Background
    val contentColor = if (isColored) White else Black

    // Top app bar template
    TopAppBar(
        modifier = modifier.background(brush = headerBrush),

        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = contentColor
                    )
                }
            }
        },

        title = {
            Text(
                text = currentView?.title ?: "WALKCORE",
                color = contentColor,
                style = MaterialTheme.typography.headlineLarge
            )
        },

        actions = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(end = 16.dp)
            ) {
                Text(
                    text = "$ $currency",
                    color = contentColor,
                    style = MaterialTheme.typography.bodyLarge
                )

                Icon(
                    imageVector = Icons.Default.Inbox,
                    contentDescription = "Mail Icon",
                    tint = contentColor
                )
            }
        },

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        )
    )
}