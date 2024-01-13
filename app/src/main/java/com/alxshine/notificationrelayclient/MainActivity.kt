package com.alxshine.notificationrelayclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.alxshine.notificationrelayclient.ui.theme.NotificationRelayClientTheme
import java.time.Instant
import java.util.UUID

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotificationRelayClientTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PreviewNotificationList()
                }
            }
        }
    }
}

data class Notification(
    val id: UUID,
    val title: String,
    val message: String,
    val tag: String,
    val expiration: Instant
)

fun sampleNotification () = Notification(
    UUID.randomUUID(),
    "Test",
    "This is a test notification :)",
    "testing",
    Instant.now()
)

var testNotifications = (0..100).map { sampleNotification() }

@Composable
fun NotificationCard(not: Notification) {
    Column {
        Text(not.title)
        Text(not.message)
    }
}

@Preview
@Composable
fun PreviewNotificationCard() {
    NotificationRelayClientTheme {
        Surface {
            NotificationCard(
                not = testNotifications[0]
            )
        }
    }
}

@Composable
private fun NotificationList(notifications: List<Notification>) {
    LazyColumn {
        items(notifications) { not ->
            NotificationCard(not)
        }
    }
}
@Preview
@Composable
fun PreviewNotificationList() {
    NotificationRelayClientTheme {
        Surface {
            NotificationList(testNotifications.slice(2..90))
        }
    }
}
