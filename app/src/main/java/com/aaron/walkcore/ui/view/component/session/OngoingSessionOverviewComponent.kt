package com.aaron.walkcore.ui.view.component.session

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.aaron.walkcore.ui.theme.BlueToYellow
import com.aaron.walkcore.ui.theme.WalkcoreTheme
import com.aaron.walkcore.ui.view.component.ButtonComponent

@Composable
fun OngoingSessionOverviewComponent(
    modifier: Modifier = Modifier,
    sessionOverview: SessionOverviewModel,
    onCardClick: () -> Unit = {},
    onButtonClick: () -> Unit
) {
    /* ==============================
    ========== UI LAYOUT ==========
    ============================== */
    // --- Layout ---
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Title
        Text(
            text = "Running Event",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
        )
        
        // Ongoing session
        SessionOverviewComponent(
            showDescription = false,
            sessionOverview = sessionOverview,
            fixedHeight = false,
            onCardClick = onCardClick
        )

        // Start session button
        ButtonComponent(
            onClick = onButtonClick,
            label = "Start Session",
            brush = BlueToYellow,
            modifier = Modifier
                .height(32.dp)
                .padding(horizontal = 32.dp)
        )
    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OngoingSessionOverviewPreview() {
    WalkcoreTheme {
        OngoingSessionOverviewComponent(
            sessionOverview = SessionOverviewDummy.SessionDummyFull,
            onButtonClick = {}
        )
    }
}