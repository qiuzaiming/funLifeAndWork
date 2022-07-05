package com.zaiming.android.funandroidcompose.splash

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.zaiming.android.funandroidcompose.R
import com.zaiming.android.funandroidcompose.ui.theme.splashBackground
import com.zaiming.android.funandroidcompose.ui.theme.splashText
import kotlinx.coroutines.delay

val LOGO_HEIGHT = 100.dp
val SPLASH_DELAY = 3000L

@Composable
inline fun Splash(
    crossinline gotoHomeScreen: () -> Unit,
) {
    var start by remember { mutableStateOf(false) }

    val offset by animateDpAsState(
        targetValue = if (start) 0.dp else 100.dp,
        animationSpec = tween(durationMillis = 1000)
    )

    val alpha by animateFloatAsState(
        targetValue = if (start) 1f else 0f,
        animationSpec = tween(durationMillis = 2000)
    )

    LaunchedEffect(key1 = Unit) {
        start = true
        delay(SPLASH_DELAY)
        gotoHomeScreen()
    }

    Splash(offsetState = offset, alphaState = alpha)
}

@Composable
fun Splash(offsetState: Dp, alphaState: Float) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.splashBackground),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .size(LOGO_HEIGHT)
                    .alpha(alphaState),
                painter = painterResource(id = getLogo()),
                contentDescription = stringResource(id = R.string.logo))

            Text(
                modifier = Modifier
                    .offset(y = offsetState)
                    .alpha(alphaState),
                text = stringResource(id = R.string.fun_life_and_work),
                color = MaterialTheme.colors.splashText,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )
        }
    }
}

@Composable
fun getLogo(): Int {
    return if (isSystemInDarkTheme()) {
        R.drawable.fun_life_and_work
    } else {
        R.drawable.fun_life_and_work
    }
}