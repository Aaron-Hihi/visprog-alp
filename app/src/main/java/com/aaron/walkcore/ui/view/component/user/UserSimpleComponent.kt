package com.aaron.walkcore.ui.view.component.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.aaron.walkcore.R
import com.aaron.walkcore.data.dummy.UserDummy
import com.aaron.walkcore.model.user.UserSimpleModel
import com.aaron.walkcore.ui.theme.LightGrey
import com.aaron.walkcore.ui.theme.WalkcoreTheme

@Composable
fun UserSimpleComponent(
    modifier: Modifier = Modifier,
    userSimpleModel: UserSimpleModel,
) {
    /* ==============================
    ========== VARIABLES ==========
    ============================== */
    var isLoadingImage by remember { mutableStateOf(true) }

    /* ==============================
    ========== UI LAYOUT ==========
    ============================== */
    //--- View vars ---
    val imageSize = 100.dp

    // --- Layout ---
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Image
        Box (
            modifier = Modifier
                .size(imageSize)
                .clip(CircleShape)
                .background(color = if (isLoadingImage) LightGrey else Color.Transparent),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = userSimpleModel.profilePictureUrl,
                contentDescription = "Profile picture of ${userSimpleModel.username}",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop,
                error = painterResource(id = R.drawable.image_placeholder),
                onLoading = { isLoadingImage = true },
                onSuccess = { isLoadingImage = false },
                onError = { isLoadingImage = false },
            )
        }

        // All text and infos
        Column(
            modifier = Modifier
                .width(imageSize),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Name
            Text(
                text = userSimpleModel.username,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )

            // Steps today
            Text(
                text = "Steps today: ${userSimpleModel.stepsWalked}",
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun UserSimplePreview() {
    WalkcoreTheme {
        UserSimpleComponent(
            userSimpleModel = UserDummy.UserDataSimpleDummy.copy(username = "wwwwwwwwwwwwwwwwwwww"),
        )
    }
}