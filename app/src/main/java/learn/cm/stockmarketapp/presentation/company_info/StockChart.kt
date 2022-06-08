package learn.cm.stockmarketapp.presentation.company_info


import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.sp
import learn.cm.stockmarketapp.domain.model.IntradayInfo
import kotlin.math.roundToInt

@Composable
fun stockChart(
    infos: List<IntradayInfo> = emptyList(),
    modifier: Modifier = Modifier,
    graphColor: Color = Color.Green
) {
    val spacing = 100f
    val transparentColor = remember{
        graphColor.copy(alpha = 0.5f)
    }
    val upperValue = remember(infos) {
        (infos.maxOfOrNull { it.close }?.plus(1))?.roundToInt() ?: 0
    }

    val lowerValue = remember(infos) {
        infos.minOfOrNull { it.close }?.toInt() ?: 0
    }
    val density = LocalDensity.current
    val textPaint = remember(density) {
        Paint().apply {
            color = android.graphics.Color.WHITE
            textAlign = Paint.Align.CENTER
            textSize = density.run { 12.sp.toPx() }
        }
    }
    Canvas(modifier = modifier){
        val spacePerHour = (size.width - spacing) / infos.size
        (0 until infos.size - 1 step 2).forEach { i ->
            val info = infos[i]
            val hour = info.date.hour
            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    hour.toString(),
                    spacing + i * spacePerHour,
                    size.height - 5,
                    textPaint
                )
            }
        }
    }
}