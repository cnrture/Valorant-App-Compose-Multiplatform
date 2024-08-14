package com.canerture.valorantcmp

import androidx.compose.runtime.Composable

@Composable
internal expect fun Notify(message: String, onDismiss: () -> Unit)