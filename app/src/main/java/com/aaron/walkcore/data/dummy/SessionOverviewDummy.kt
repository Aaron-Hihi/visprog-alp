package com.aaron.walkcore.data.dummy

import com.aaron.walkcore.model.session.SessionOverviewModel

object SessionOverviewDummy {
    val SessionDummyFull = SessionOverviewModel(
        id = "sess_001",
        title = "Lari Pagi Keliling Monas",
        creatorUsername = "budi_sutanto",
        description = "Ayo kita kumpulkan langkah sambil menikmati udara segar Jakarta! Rute santai, cocok untuk pemula. Jangan lupa bawa air minum dan pakai sepatu yang nyaman.",
        // Waktu 1 jam di UTC, nanti akan dikonversi ke waktu lokal
        dateTimeRange = "17 Desember 2025, 13:00 - 14:30 WIB",
        imageUrl = "https://picsum.photos/400/400",
        locationName = "Taman Monas, Jakarta Pusat"
    )

    val SessionDummyRemote = SessionOverviewModel(
        id = "sess_002",
        title = "Tantangan 10.000 Langkah Harian",
        creatorUsername = "fit_challenge_pro",
        description = "Sesi tantangan yang bisa dilakukan di mana saja! Cukup pastikan aplikasi pelacak langkah Anda terhubung. Siapa cepat, dia dapat lencana!",
        dateTimeRange = "17 Desember 2025, 13:00 - 14:30 WIB",
        imageUrl = "",
        locationName = null
    )

    val SessionDummyMinimal = SessionOverviewModel(
        id = "sess_003",
        title = "Jalan Kaki Sore di Sekitar CFD",
        creatorUsername = "dewi_anggraini",
        description = "Sesi jalan sore singkat. Kumpul di pintu masuk.",
        dateTimeRange = "17 Desember 2025, 13:00 - 14:30 WIB",
        imageUrl = null,
        locationName = "Area CFD Sudirman, Jakarta"
    )

    val allSessions = listOf<SessionOverviewModel>(
        SessionDummyFull,
        SessionDummyRemote,
        SessionDummyMinimal
    )
}