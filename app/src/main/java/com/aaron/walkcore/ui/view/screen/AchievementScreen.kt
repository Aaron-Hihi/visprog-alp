package com.aaron.walkcore.ui.view.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aaron.walkcore.ui.theme.WalkcoreTheme
import com.aaron.walkcore.ui.view.component.AppHeader

// Simple data model for the UI
data class Achievement(
    val id: Int,
    val title: String,
    val description: String,
    val progress: Float, // 0.0 to 1.0
    val isUnlocked: Boolean
)

@Composable
fun AchievementScreen() {
    // Placeholder Data
    val achievementList = listOf(
        Achievement(1, "First Steps", "Walk your first 1,000 steps.", 1.0f, true),
        Achievement(2, "Marathoner", "Walk a total of 42km.", 0.75f, false),
        Achievement(3, "Early Bird", "Complete a walk before 7 AM.", 1.0f, true),
        Achievement(4, "Night Owl", "Walk 5,000 steps after 8 PM.", 0.3f, false),
        Achievement(5, "Social Walker", "Add 5 friends.", 0.0f, false),
        Achievement(6, "Consistent", "Reach goal 7 days in a row.", 0.5f, false),
        Achievement(7, "Explorer", "Visit 10 different locations.", 0.2f, false)
    )

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            // 1. Header
            AppHeader(
                currency = 12000L, // Example currency
                isColored = true
            )

            // 2. Title Section
            Padding(16.dp) {
                Text(
                    text = "Achievements",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            // 3. Scrollable List
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(achievementList) { achievement ->
                    AchievementRow(achievement)
                }
            }
        }
    }
}

@Composable
fun AchievementRow(item: Achievement) {
    // determine colors based on unlocked state
    val containerColor = if (item.isUnlocked) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
    val iconColor = if (item.isUnlocked) Color(0xFFFFD700) else Color.Gray // Gold if unlocked
    val icon = if (item.isUnlocked) Icons.Default.EmojiEvents else Icons.Default.Lock

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        elevation = CardDefaults.cardElevation(defaultElevation = if (item.isUnlocked) 4.dp else 0.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon Circle
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.background),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconColor,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Text and Progress
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = if (item.isUnlocked) MaterialTheme.colorScheme.onSurface else Color.Gray
                )
                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = if (item.isUnlocked) MaterialTheme.colorScheme.onSurfaceVariant else Color.Gray
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Progress Bar
                Row(verticalAlignment = Alignment.CenterVertically) {
                    LinearProgressIndicator(
                        progress = { item.progress },
                        modifier = Modifier
                            .weight(1f)
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp)),
                        color = if (item.isUnlocked) MaterialTheme.colorScheme.primary else Color.Gray,
                        trackColor = MaterialTheme.colorScheme.outlineVariant,
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "${(item.progress * 100).toInt()}%",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

// Helper for cleaner code
@Composable
fun Padding(padding: androidx.compose.ui.unit.Dp, content: @Composable () -> Unit) {
    Box(modifier = Modifier.padding(padding)) { content() }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AchievementScreenPreview() {
    WalkcoreTheme {
        AchievementScreen()
    }
}