package com.aaron.walkcore.data.dummy

import com.aaron.walkcore.model.enums.SessionMode
import com.aaron.walkcore.model.enums.SessionStatus
import com.aaron.walkcore.model.enums.SessionVisibility
import com.aaron.walkcore.model.session.SessionDetailsModel
import com.aaron.walkcore.model.user.UserSimpleModel

object SessionDummy {
    // Dummy User (untuk mengisi list participants)
    private val user1 = UserSimpleModel("u01", "budi_sutanto", "https://picsum.photos/40/40?r=1", 12000)
    private val user2 = UserSimpleModel("u02", "rara_newbie", "https://picsum.photos/40/40?r=2", 8450)
    private val user3 = UserSimpleModel("u03", "alex_walker_pro", "https://picsum.photos/40/40?r=3", 15100)
    private val user4 = UserSimpleModel("u04", "dewi_anggraini", "https://picsum.photos/40/40?r=4", 6200)

    // Skenario 1: Sesi Penuh (Mode SOLO/Lokal, Status PLANNED/Terencana, Visibility PUBLIC)
    // Sesi yang akan segera dimulai dan memiliki lokasi fisik (SOLO = Sesi fisik/Non-remote)
    val SessionDetailPlannedSoloPublic = SessionDetailsModel(
        id = "sess_001",
        title = "Lari Pagi Keliling Monas",
        creatorUsername = "budi_sutanto",
        description = "Ayo kita kumpulkan langkah sambil menikmati udara segar Jakarta! Rute santai, cocok untuk pemula. Jangan lupa bawa air minum dan pakai sepatu yang nyaman.",
        dateTimeRange = "17 Desember 2025, 13:00 - 14:30 WIB", // Hari Sama
        imageUrl = "https://picsum.photos/800/600?random=1",
        locationName = "Taman Monas, Jakarta Pusat",

        mode = SessionMode.SOLO,
        status = SessionStatus.PLANNED,
        visibility = SessionVisibility.PUBLIC,

        maxParticipants = 15,
        currentParticipants = listOf(user1, user2, user3), // Baru sedikit peserta
        stepTarget = 10000,

        createdAt = "2025-12-10T09:00:00Z",
        updatedAt = "2025-12-16T10:00:00Z"
    )

    // Skenario 2: Sesi Jarak Jauh (Mode REMOTE, Status ONGOING, Visibility INVITEONLY)
    // Sesi yang sedang berjalan, tidak punya lokasi fisik, dan hanya untuk undangan
    val SessionDetailOngoingRemoteInvite = SessionDetailsModel(
        id = "sess_002",
        title = "Tantangan 24 Jam: 30.000 Langkah",
        creatorUsername = "rara_newbie",
        description = "Sesi tantangan yang bisa dilakukan di mana saja! Ini adalah tantangan pribadi untuk tim inti kami. Lokasi tidak relevan. Sedang berlangsung!",
        dateTimeRange = "16 Des 2025 22:00 - 17 Des 2025 19:00 WIB", // Lintas hari
        imageUrl = null,
        locationName = null, // Remote

        mode = SessionMode.REMOTE,
        status = SessionStatus.ONGOING,
        visibility = SessionVisibility.INVITE_ONLY, // Hanya undangan

        maxParticipants = 5,
        currentParticipants = listOf(user2, user3),
        stepTarget = 30000,

        createdAt = "2025-12-15T00:00:00Z",
        updatedAt = "2025-12-16T12:00:00Z"
    )

    // Skenario 3: Sesi Selesai (Mode SOLO, Status FINISHED, Visibility FRIENDONLY)
    val SessionDetailFinishedFriendOnly = SessionDetailsModel(
        id = "sess_003",
        title = "Jalan Santai Sore Kota Tua",
        creatorUsername = "alex_walker_pro",
        description = "Sesi jalan sore singkat di sekitar bangunan bersejarah. Sempurna untuk mengakhiri hari.",
        dateTimeRange = "18 Desember 2025, 17:00 - 17:45 WIB",
        imageUrl = "https://picsum.photos/800/600?random=2",
        locationName = "Taman Fatahillah, Kota Tua",

        mode = SessionMode.SOLO,
        status = SessionStatus.FINISHED, // Selesai
        visibility = SessionVisibility.FRIEND_ONLY, // Hanya Teman

        maxParticipants = 30,
        currentParticipants = listOf(user3, user1, user4),
        stepTarget = 5000,

        createdAt = "2025-12-10T00:00:00Z",
        updatedAt = "2025-12-18T18:00:00Z"
    )

    // Skenario 4: Sesi Dibatalkan (Mode SOLO, Status CANCELLED)
    val SessionDetailCancelled = SessionDetailsModel(
        id = "sess_004",
        title = "Lari Malam di Kawasan SCBD",
        creatorUsername = "budi_sutanto",
        description = "Sesi lari malam dibatalkan karena perkiraan hujan lebat. Akan dijadwal ulang.",
        dateTimeRange = "20 Desember 2025, 20:00 - 21:00 WIB",
        imageUrl = null,
        locationName = "SCBD Park, Jakarta",

        mode = SessionMode.SOLO,
        status = SessionStatus.CANCELLED, // Dibatalkan
        visibility = SessionVisibility.PUBLIC,

        maxParticipants = 20,
        currentParticipants = listOf(user1),
        stepTarget = 8000,

        createdAt = "2025-12-19T10:00:00Z",
        updatedAt = "2025-12-20T15:00:00Z"
    )

    val allSessionDetails = listOf(
        SessionDetailPlannedSoloPublic,
        SessionDetailOngoingRemoteInvite,
        SessionDetailFinishedFriendOnly,
        SessionDetailCancelled
    )
}