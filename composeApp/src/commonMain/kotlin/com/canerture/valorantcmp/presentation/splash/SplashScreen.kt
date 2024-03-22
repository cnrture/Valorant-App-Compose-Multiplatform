package com.canerture.valorantcmp.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.canerture.valorantcmp.navigation.MainNavigator
import com.canerture.valorantcmp.presentation.theme.ValorantTheme
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import valorantcmp.composeapp.generated.resources.Res
import valorantcmp.composeapp.generated.resources.desc_valorant_logo
import valorantcmp.composeapp.generated.resources.ic_valorant

class SplashScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(ValorantTheme.colors.secondary),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .sizeIn(maxWidth = 300.dp, maxHeight = 600.dp)
                    .fillMaxSize()
                    .background(ValorantTheme.colors.secondary)
                    .padding(40.dp),
                painter = painterResource(Res.drawable.ic_valorant),
                contentDescription = stringResource(Res.string.desc_valorant_logo)
            )
        }

        LaunchedEffect(Unit) {
            delay(2000)
            navigator.replace(MainNavigator())
        }
    }
}
