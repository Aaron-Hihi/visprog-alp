package com.aaron.walkcore.ui.view.component.user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aaron.walkcore.data.dummy.UserDummy
import com.aaron.walkcore.model.user.UserSimpleModel
import com.aaron.walkcore.ui.theme.WalkcoreTheme

@Composable
fun ListUserSimpleComponent(
    modifier: Modifier = Modifier,
    title: String? = null,
    button: (@Composable () -> Unit)? = null,
    userSimpleModels: List<UserSimpleModel>
) {
    val displayedUsers = userSimpleModels.take(20)

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.Start
    ) {
        // Header alignment matching global app padding
        if (title != null || button != null) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                if (title != null) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.headlineMedium,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .weight(1f, fill = false)
                            .padding(end = 8.dp)
                    )
                }

                if (button != null) {
                    button()
                }
            }
        }

        // User list with horizontal scroll touching the display edges
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(
                items = displayedUsers,
                key = { user -> user.id }
            ) { user ->
                UserSimpleComponent(
                    userSimpleModel = user
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ListUserSimplePreview() {
    WalkcoreTheme {
        ListUserSimpleComponent(
            userSimpleModels = UserDummy.AllSimpleUsers
        )
    }
}