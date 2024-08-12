package com.canerture.valorantcmp.common

import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.ColorSpaces
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.util.lerp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.github.ajalt.colormath.model.LABColorSpaces.LAB50
import com.github.ajalt.colormath.model.RGBColorSpaces.ACES
import com.github.ajalt.colormath.model.RGBColorSpaces.ACEScg
import com.github.ajalt.colormath.model.RGBColorSpaces.AdobeRGB
import com.github.ajalt.colormath.model.RGBColorSpaces.BT2020
import com.github.ajalt.colormath.model.RGBColorSpaces.BT709
import com.github.ajalt.colormath.model.RGBColorSpaces.DCI_P3
import com.github.ajalt.colormath.model.RGBColorSpaces.DisplayP3
import com.github.ajalt.colormath.model.RGBColorSpaces.LinearSRGB
import com.github.ajalt.colormath.model.RGBColorSpaces.ROMM_RGB
import com.github.ajalt.colormath.model.RGBInt
import com.github.ajalt.colormath.model.SRGB
import com.github.ajalt.colormath.model.XYZColorSpaces.XYZ50
import com.github.ajalt.colormath.parse
import kotlinx.coroutines.flow.Flow
import kotlin.math.absoluteValue

fun colorParse(color: String): Color {
    return com.github.ajalt.colormath.Color.parse("#$color").toComposeColor()
}

fun com.github.ajalt.colormath.Color.toComposeColor(): Color {
    return if (this is RGBInt) {
        Color(argb.toInt())
    } else {
        val s = when {
            space === SRGB -> ColorSpaces.Srgb
            space === ACES -> ColorSpaces.Aces
            space === ACEScg -> ColorSpaces.Acescg
            space === AdobeRGB -> ColorSpaces.AdobeRgb
            space === BT2020 -> ColorSpaces.Bt2020
            space === BT709 -> ColorSpaces.Bt709
            space === LAB50 -> ColorSpaces.CieLab
            space === XYZ50 -> ColorSpaces.CieXyz
            space === DCI_P3 -> ColorSpaces.DciP3
            space === DisplayP3 -> ColorSpaces.DisplayP3
            space === LinearSRGB -> ColorSpaces.LinearSrgb
            space === ROMM_RGB -> ColorSpaces.ProPhotoRgb
            else -> null
        }

        if (s == null) {
            val (r, g, b, a) = toSRGB()
            Color(r, g, b, a)
        } else {
            val (r, g, b, a) = toArray()
            Color(r, g, b, a, s)
        }
    }
}

fun Modifier.carouselTransition(page: Int, pagerState: PagerState) =
    graphicsLayer {
        val pageOffset = ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue
        val transformation = lerp(start = 0.9f, stop = 1f, fraction = 1f - pageOffset.coerceIn(0f, 1f))
        alpha = transformation
        scaleY = transformation
    }

@Composable
fun <T> Flow<T>.collectWithLifecycle(
    collect: suspend (T) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(this, lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            this@collectWithLifecycle.collect { effect ->
                collect(effect)
            }
        }
    }
}