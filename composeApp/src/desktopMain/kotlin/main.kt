import androidx.compose.ui.Alignment
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.canerture.valorantcmp.App
import com.canerture.valorantcmp.di.initKoin
import java.awt.Dimension

private const val WINDOW_WIDTH = 1280
private const val WINDOW_HEIGHT = 760

fun main() = application {
    initKoin()
    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition(Alignment.Center)
    )
    Window(title = "Valorant CMP", onCloseRequest = ::exitApplication, state = state) {
        window.minimumSize = Dimension(WINDOW_WIDTH, WINDOW_HEIGHT)
        App()
    }
}
