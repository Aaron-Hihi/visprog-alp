package com.aaron.walkcore.ui.view.component.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
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
fun RowUserSimpleComponent(
    modifier: Modifier = Modifier,
    userSimpleModel: UserSimpleModel,
    showSteps: Boolean = true
) {
    var isLoadingImage by remember { mutableStateOf(true) }

    // User identification row
    Row (
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // Profile image with circular clipping
        Box (
            modifier = Modifier
                .size(56.dp)
                .background(
                    color = if (isLoadingImage) LightGrey else Color.Transparent,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = userSimpleModel.profilePictureUrl,
                contentDescription = "Profile picture of ${userSimpleModel.username}",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape), // Ensure the image itself is clipped to a circle
                contentScale = ContentScale.Crop,
                error = painterResource(id = R.drawable.image_placeholder),
                onLoading = { isLoadingImage = true },
                onSuccess = { isLoadingImage = false },
                onError = { isLoadingImage = false },
            )
        }

        // Identification and metric column
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.Start
        ) {
            // Username display
            Text(
                text = userSimpleModel.username,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            // Dynamic metric display
            if (showSteps) {
                Text(
                    text = "${userSimpleModel.stepsWalked} steps",
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RowUserSimplePreview() {
    WalkcoreTheme {
        RowUserSimpleComponent(
            userSimpleModel = UserDummy.UserDataSimpleDummy,
            showSteps = true
        )
    }
}