package com.aaron.walkcore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aaron.walkcore.data.dummy.SessionOverviewDummy
import com.aaron.walkcore.ui.theme.WalkcoreTheme
import com.aaron.walkcore.ui.view.component.SessionOverviewComponent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WalkcoreTheme {
                SessionOverviewComponent(
                    sessionOverview = SessionOverviewDummy.SessionDummyFull,
                    showDescription = true
                )
            }
        }
    }
}