package com.canerture.valorantcmp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogWindow

@Composable
internal actual fun Notify(message: String, onDismiss: () -> Unit) {
    DialogWindow(
        title = "Something went wrong!",
        onCloseRequest = { onDismiss() },
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = message,
                    fontSize = 24.sp,
                )
            }
        }
    )
}