package com.aaron.walkcore.ui.screen.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.aaron.walkcore.ui.theme.WalkcoreTheme
import java.time.YearMonth

// ==========================
// MAIN SCREEN (FIXED)
// ==========================
@Composable
fun ScheduleScreen() {

    var showPopup by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {

        // ===== MAIN CONTENT =====
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            ScheduleHeader()

            Column(modifier = Modifier.padding(16.dp)) {

                SetCalendarButton(
                    onClick = { showPopup = true }
                )

                Spacer(modifier = Modifier.height(16.dp))

                SwipeableCalendar()

                Spacer(modifier = Modifier.height(16.dp))

                UpcomingScheduleCard()
            }
        }

        // ===== POPUP LAYER (TOP MOST) =====
        if (showPopup) {
            AddSchedulePopup(
                onDismiss = { showPopup = false }
            )
        }
    }
}

// ==========================
// HEADER
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
private fun SetCalendarButton(
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text("Set Calendar")
    }
}

// ==========================
// POPUP (FIXED & VISIBLE)
// ==========================
@Composable
fun AddSchedulePopup(
    onDismiss: () -> Unit
) {
    var showDetail by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf("") }
    var syncGoogleCalendar by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.45f)),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(10.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Tambah Jadwal",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.weight(1f)
                    )

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Sync ", fontSize = 12.sp)
                        Switch(
                            checked = syncGoogleCalendar,
                            onCheckedChange = { syncGoogleCalendar = it }
                        )
                    }
                }

                Divider()

                // Calendar UI (simple)
                CalendarGrid(
                    onDateSelected = {
                        selectedDate = it
                        showDetail = true
                    }
                )

                Button(
                    onClick = onDismiss,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("Tutup")
                }
            }
        }
    }

    if (showDetail) {
        ScheduleDetailPopup(
            date = selectedDate,
            onDismiss = { showDetail = false }
        )
    }
}


@Composable
fun CalendarGrid(
    onDateSelected: (String) -> Unit
) {
    val days = (1..30).toList()

    Column {
        days.chunked(7).forEach { week ->
            Row(modifier = Modifier.fillMaxWidth()) {
                week.forEach { day ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(4.dp)
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                            .clickable {
                                onDateSelected("2026-01-$day")
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(day.toString())
                    }
                }
            }
        }
    }
}


@Composable
fun ScheduleDetailPopup(
    date: String,
    onDismiss: () -> Unit
) {
    var time by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.45f)),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(10.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Text(
                    text = "Detail Jadwal",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )

                Text("Tanggal: $date", fontSize = 14.sp)

                OutlinedTextField(
                    value = time,
                    onValueChange = { time = it },
                    label = { Text("Waktu (contoh: 07:00)") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Deskripsi") },
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        onClick = onDismiss,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Cancel")
                    }

                    Button(
                        onClick = {
                            // nanti logic simpan
                            onDismiss()
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Apply")
                    }
                }
            }
        }
    }
}


// ==========================
// OPTION BUTTON
// ==========================
@Composable
private fun ScheduleOptionButton(text: String) {
    OutlinedButton(
        onClick = {},
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(text)
    }
}

// ==========================
// SWIPEABLE CALENDAR
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
            CalendarCard(baseMonth.plusMonths(index.toLong()))
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

    Card(
        modifier = Modifier.width(300.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = "$monthName ${yearMonth.year}",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(8.dp))
            CalendarWeekHeader()
            CalendarGrid(yearMonth.lengthOfMonth())
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
                textAlign = TextAlign.Center
            )
        }
    }
}

// ==========================
// CALENDAR GRID
// ==========================
@Composable
private fun CalendarGrid(daysInMonth: Int) {

    BoxWithConstraints {
        val cellSize = maxWidth / 7

        Column {
            (1..daysInMonth).chunked(7).forEach { week ->
                Row {
                    week.forEach {
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
                            Text(it.toString())
                        }
                    }
                }
            }
        }
    }
}

// ==========================
// UPCOMING
// ==========================
@Composable
private fun UpcomingScheduleCard() {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Upcoming Schedule", fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(8.dp))
            ScheduleItem("Morning Walk", "06:00 AM")
            ScheduleItem("Evening Jog", "05:30 PM")
            ScheduleItem("Mukbang Run", "07:00 PM")
        }
    }
}

@Composable
private fun ScheduleItem(title: String, time: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
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

@Preview(showBackground = true)
@Composable
private fun AddSchedulePopupPreview() {
    WalkcoreTheme {
        AddSchedulePopup(onDismiss = {})
    }
}
