package me.ldgomm.morpheus.app.main.view.auth

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons.Default
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import java.net.ConnectException
import java.net.SocketTimeoutException

data class SuccessErrorMessageBar(val message: String? = null, val error: Exception? = null)

@Composable
fun SuccessErrorMessageBarView(successErrorMessageBarState: SuccessErrorMessageBar) {
    val startAnimation by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    LaunchedEffect(key1 = successErrorMessageBarState.error) {
        if (successErrorMessageBarState.error != null) {
            errorMessage = when (successErrorMessageBarState.error) {
                is SocketTimeoutException -> {
                    "Connection timeout"
                }
                is ConnectException -> {
                    "Internet connection unavailable"
                }
                else -> {
                    "${successErrorMessageBarState.error.message}"
                }
            }
        }
        !startAnimation
        delay(3000)
        !startAnimation
    }

    AnimatedVisibility(visible = successErrorMessageBarState.error != null && startAnimation || successErrorMessageBarState.message != null && startAnimation,
                       enter = expandVertically(animationSpec = tween(durationMillis = 300),
                                                expandFrom = Alignment.Top),
                       exit = shrinkVertically(animationSpec = tween(durationMillis = 300),
                                               shrinkTowards = Alignment.Top)) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(if (successErrorMessageBarState.error != null) Green else Red)
            .padding(horizontal = 20.dp)) {
            Icon(imageVector = if (successErrorMessageBarState.error != null) Default.Warning else Default.Check,
                 contentDescription = "")
            Divider(Modifier.width(12.dp), color = Transparent)
            Text(text = if (successErrorMessageBarState.error != null) errorMessage else successErrorMessageBarState.message.toString(),
                 style = TextStyle(color = White, fontSize = 13.sp, fontWeight = Bold),
                 overflow = Ellipsis)
        }
    }
}