package me.ldgomm.morpheus.app.main.view.auth

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Unspecified
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import me.ldgomm.morpheus.R.drawable.google_logo

@Composable
fun SignInWithGoogleButtonView(modifier: Modifier = Modifier,
                               signedInState: Boolean = false,
                               primaryText: String = "Sign in with Google",
                               secondaryText: String = "Please wait...",
                               icon: Int = google_logo,
                               shape: CornerBasedShape = ShapeDefaults.Large,
                               borderColor: Color = LightGray,
                               backgroundColor: Color = MaterialTheme.colorScheme.surface,
                               borderStrokeWidth: Dp = 1.dp,
                               onSignInWithGoogleButtonClicked: () -> Unit) {
    var buttonText by remember { mutableStateOf(primaryText) }

    LaunchedEffect(key1 = signedInState) {
        buttonText = if (signedInState) secondaryText else primaryText
    }

    Surface(modifier = modifier.clickable(enabled = !signedInState) { onSignInWithGoogleButtonClicked() },
            shape = shape,
            border = BorderStroke(width = borderStrokeWidth, color = borderColor),
            color = backgroundColor) {
        Row(modifier = Modifier
            .padding(12.dp)
            .animateContentSize(animationSpec = tween(durationMillis = 300,
                                                      easing = LinearOutSlowInEasing)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center) {
            Icon(painter = painterResource(id = icon), "Google Logo", tint = Unspecified)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = buttonText)
            if (signedInState) {
                Spacer(modifier = Modifier.width(16.dp))
                CircularProgressIndicator(modifier = Modifier.size(17.dp), strokeWidth = 2.dp)
            }
        }
    }
}