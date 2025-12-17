package com.aaron.walkcore.ui.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aaron.walkcore.ui.theme.Blue
import com.aaron.walkcore.ui.theme.Green
import com.aaron.walkcore.ui.theme.WalkcoreTheme
import com.aaron.walkcore.ui.theme.White

@Composable
fun ButtonComponent(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    label: String,
    brush: Brush
) {
    /* ==============================
    ========== UI LAYOUT ==========
    ============================== */
    // --- Layout ---
    // Button and background
    Surface(
        modifier = modifier
            .heightIn(min = 32.dp),
        shape = RoundedCornerShape(24.dp),
        shadowElevation = 4.dp,
        color = Color.Transparent,
        onClick = onClick
    ) {

        // Color
        Box(
            modifier = Modifier
                .background(brush)
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {

            // Text
            Text(
                text = label,
                style = MaterialTheme.typography.bodyLarge,
                color = White
            )
        }
    }
}

@Composable
fun ButtonComponent(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    label: String,
    color: Color
) {
    ButtonComponent(modifier, onClick, label, SolidColor(color))
}


@Preview(showBackground = true, showSystemUi = false)
@Composable
fun ButtonPreview() {
    WalkcoreTheme {
        ButtonComponent(
            onClick = {},
            label = "Join Now",
            brush = Brush.linearGradient(
                colors = listOf(
                    Blue,
                    Green
                )
            )
        )
    }
}