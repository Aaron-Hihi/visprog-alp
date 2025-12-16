package com.aaron.walkcore.ui.screen.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.aaron.walkcore.ui.theme.WalkcoreTheme
import java.time.YearMonth

// ==========================
// MAIN SCREEN
// ==========================
@Composable
fun ScheduleScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        ScheduleHeader()

        Column(modifier = Modifier.padding(16.dp)) {

            SetCalendarButton()

            Spacer(modifier = Modifier.height(16.dp))

            SwipeableCalendar()

            Spacer(modifier = Modifier.height(16.dp))

            UpcomingScheduleCard()
        }
    }
}

// ==========================
// HEADER (PLACEHOLDER)
// ==========================
@Composable
private fun ScheduleHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Schedule",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

// ==========================
// SET CALENDAR BUTTON
// ==========================
@Composable
private fun SetCalendarButton() {
    Button(
        onClick = { /* UI only */ },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text("Set Calendar")
    }
}

// ==========================
// SWIPEABLE CALENDAR (SAFE VERSION)
// ==========================
@Composable
private fun SwipeableCalendar() {

    val baseMonth = remember { YearMonth.now() }

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        items((0 until 24).toList()) { index ->
            val yearMonth = baseMonth.plusMonths(index.toLong())
            CalendarCard(yearMonth)
        }
    }
}

// ==========================
// CALENDAR CARD
// ==========================
@Composable
private fun CalendarCard(yearMonth: YearMonth) {

    val monthName = yearMonth.month.name
        .lowercase()
        .replaceFirstChar { it.uppercase() }

    val daysInMonth = yearMonth.lengthOfMonth()

    Card(
        modifier = Modifier
            .width(300.dp)
            .wrapContentHeight(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = "$monthName ${yearMonth.year}",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(12.dp))

            CalendarWeekHeader()
            Spacer(modifier = Modifier.height(8.dp))
            CalendarGrid(daysInMonth)
        }
    }
}

// ==========================
// WEEK HEADER
// ==========================
@Composable
private fun CalendarWeekHeader() {
    Row(modifier = Modifier.fillMaxWidth()) {
        listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun").forEach {
            Text(
                text = it,
                modifier = Modifier.weight(1f),
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

// ==========================
// CALENDAR GRID (28–31 DAYS)
// ==========================
@Composable
private fun CalendarGrid(daysInMonth: Int) {

    val days = (1..daysInMonth).toList()

    BoxWithConstraints(
        modifier = Modifier.fillMaxWidth()
    ) {
        val cellSize = maxWidth / 7

        Column {
            days.chunked(7).forEach { week ->
                Row {
                    week.forEach { day ->
                        Box(
                            modifier = Modifier
                                .size(cellSize)
                                .padding(4.dp)
                                .background(
                                    MaterialTheme.colorScheme.primary.copy(alpha = 0.15f),
                                    RoundedCornerShape(8.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(day.toString())
                        }
                    }

                    // Isi sisa kolom agar baris terakhir tetap rapi
                    repeat(7 - week.size) {
                        Spacer(
                            modifier = Modifier
                                .size(cellSize)
                                .padding(4.dp)
                        )
                    }
                }
            }
        }
    }
}
// ==========================
// UPCOMING SCHEDULE
// ==========================
@Composable
private fun UpcomingScheduleCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = "Upcoming Schedule",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(12.dp))

            //Dummy data
            ScheduleItem("Morning Walk", "06:00 AM")
            ScheduleItem("Evening Jog", "05:30 PM")
            ScheduleItem("Mukbang run", "07:00 PM")
        }
    }
}

@Composable
private fun ScheduleItem(title: String, time: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(title)
        Text(time, fontWeight = FontWeight.Bold)
    }
}

// ==========================
// PREVIEW
// ==========================
@Preview(showBackground = true)
@Composable
private fun ScheduleScreenPreview() {
    WalkcoreTheme {
        ScheduleScreen()
    }
}
