package com.aaron.walkcore.ui.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aaron.walkcore.ui.theme.Black
import com.aaron.walkcore.ui.theme.Blue
import com.aaron.walkcore.ui.theme.Green
import com.aaron.walkcore.ui.theme.WalkcoreTheme
import com.aaron.walkcore.ui.theme.White
import com.aaron.walkcore.ui.theme.Yellow

@Composable
fun LabelValueComponent(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    brush: Brush
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
    // --- View Vars ---
    val boxHeight = 120.dp


    // --- Layout ---

    // Background
    Box(
        modifier = modifier
            .background(
                brush = brush,
                shape = RoundedCornerShape(16.dp)
            )
            .height(boxHeight),
        contentAlignment = Alignment.Center
    ) {

        // Layout (Downwards)
        Column (
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            // Label text goes here
            Text (
                text = label,
                textAlign = TextAlign.Center,
                color = Black,
                style = MaterialTheme.typography.headlineMedium,
                maxLines = 2
            )

            // Value text goes here
            Text (
                text = value,
                textAlign = TextAlign.Center,
                color = Black,
                style = MaterialTheme.typography.headlineLarge,
                maxLines = 1
            )
        }
    }
}

@Composable
fun LabelValueComponent(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    color: Color
) {
    LabelValueComponent(modifier, label, value, SolidColor(color))
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LabelValuePreview() {
    WalkcoreTheme {
        LabelValueComponent(label = "Steps\nToday", value = "389/2000",
            brush = Brush.horizontalGradient(
            colors = listOf(Yellow, Green)
        ))
    }
}