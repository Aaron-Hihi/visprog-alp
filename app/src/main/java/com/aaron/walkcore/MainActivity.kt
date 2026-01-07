package com.aaron.walkcore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.aaron.walkcore.route.AppRouting
import com.aaron.walkcore.ui.theme.WalkcoreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WalkcoreTheme {
                AppRouting()
            }
        }
    }
}