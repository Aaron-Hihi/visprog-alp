package com.aaron.walkcore.ui.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aaron.walkcore.ui.theme.Black
import com.aaron.walkcore.ui.theme.Blue
import com.aaron.walkcore.ui.theme.Green
import com.aaron.walkcore.ui.theme.WalkcoreTheme
import com.aaron.walkcore.ui.theme.White

@Composable
fun AppHeader(
    modifier: Modifier = Modifier,
    currency: Long,
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
        Brush.horizontalGradient(
            colors = listOf(Blue, Green)
        )
    } else {
        SolidColor(Color.Transparent)
    }

    // --- Layout ---

    // Background
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(brush = headerBrush)
            .padding(horizontal = 16.dp, vertical = 32.dp)
    ) {

        // Top row: Title, Currency, Mail
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Title
            Text(
                text = "WALKCORE",
                color = if (isColored) White else Black,
                style = MaterialTheme.typography.headlineLarge
            )

            // Row for currency and mail
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(
                    space = 8.dp,
                    alignment = Alignment.CenterHorizontally
                ),
            ) {
                // Currency
                Text(
                    text = "$ 10000", // TODO: Change to viewmodel data
                    color = if (isColored) White else Black,
                    style = MaterialTheme.typography.bodyLarge
                )

                // Mail Icon
                Icon(
                    imageVector = Icons.Default.Inbox,
                    contentDescription = "Mail Icon",
                    tint = if (isColored) White else Black
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppHeaderPreview() {
    WalkcoreTheme {
        AppHeader(currency = 10000, isColored = true)
    }
}