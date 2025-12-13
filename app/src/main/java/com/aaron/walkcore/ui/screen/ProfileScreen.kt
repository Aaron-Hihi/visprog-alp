package com.aaron.walkcore.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.aaron.walkcore.ui.theme.WalkcoreTheme

// ==========================
// DATA DUMMY (UI ONLY)
// ==========================
data class ProfileUiData(
    val gender: String,
    val heightCm: Int,
    val weightKg: Int,
    val birthday: String,
    val stepsWalked: Int,
    val distanceKm: Float,
    val activeTimeMin: Int,
    val sessionTimeMin: Int,
    val sessionCount: Int,
    val caloriesBurned: Int,
    val averagePace: Float,
    val streakDays: Int
)

// ==========================
// MAIN PROFILE SCREEN
// ==========================
@Composable
fun ProfileScreen() {

    val profile = ProfileUiData(
        gender = "Male",
        heightCm = 170,
        weightKg = 65,
        birthday = "12 Aug 2003",
        stepsWalked = 12450,
        distanceKm = 8.4f,
        activeTimeMin = 90,
        sessionTimeMin = 45,
        sessionCount = 12,
        caloriesBurned = 540,
        averagePace = 6.2f,
        streakDays = 5
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        ProfileHeader()

        Column(modifier = Modifier.padding(16.dp)) {
            BioCard(profile)
            Spacer(modifier = Modifier.height(16.dp))
            ActivityCard(profile)
        }
    }
}

// ==========================
// HEADER (BANNER + AVATAR + EDIT)
// ==========================
@Composable
fun ProfileHeader() {
    Box(modifier = Modifier.fillMaxWidth()) {

        // Banner
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
                )
        )

        // Edit Button
        IconButton(
            onClick = { /* nanti edit */ },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(12.dp)
                .size(36.dp)
                .background(
                    color = Color.White.copy(alpha = 0.85f),
                    shape = RoundedCornerShape(10.dp)
                )
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Edit Profile",
                tint = Color.Black
            )
        }

        // Avatar
        Box(
            modifier = Modifier
                .size(110.dp)
                .align(Alignment.BottomCenter)
                .offset(y = 55.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Avatar",
                fontWeight = FontWeight.Bold
            )
        }
    }

    Spacer(modifier = Modifier.height(70.dp))
}

// ==========================
// BIO CARD
// ==========================
@Composable
fun BioCard(profile: ProfileUiData) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Bio",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )

                // EDIT BUTTON (UI ONLY)
                IconButton(
                    onClick = {
                        // TODO: Edit Bio (lanjut nanti)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit Bio"
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            ProfileRow("Gender", profile.gender)
            ProfileRow("Height", "${profile.heightCm} cm")
            ProfileRow("Weight", "${profile.weightKg} kg")
            ProfileRow("Birthday", profile.birthday)
        }
    }
}


// ==========================
// ACTIVITY SUMMARY CARD
// ==========================
@Composable
fun ActivityCard(profile: ProfileUiData) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = "Activity Summary",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = "Automatically calculated by system",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(12.dp))

            ProfileRow("Steps Walked", profile.stepsWalked.toString())
            ProfileRow("Distance Travelled", "${profile.distanceKm} km")
            ProfileRow("Active Time", "${profile.activeTimeMin} min")
            ProfileRow("Session Time", "${profile.sessionTimeMin} min")
            ProfileRow("Amount of Session", profile.sessionCount.toString())
            ProfileRow("Calories Burned", "${profile.caloriesBurned} kcal")
            ProfileRow("Average Pace", "${profile.averagePace} min/km")
            ProfileRow("Streak", "${profile.streakDays} days")
        }
    }
}


// ==========================
// ROW COMPONENT
// ==========================
@Composable
fun ProfileRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label)
        Text(text = value, fontWeight = FontWeight.Bold)
    }
}

// ==========================
// PREVIEW
// ==========================
@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    WalkcoreTheme {
        ProfileScreen()
    }
}
