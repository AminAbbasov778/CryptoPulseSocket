package com.example.cryptoapp.detailscreen.presentation.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.example.cryptoapp.detailscreen.presentation.events.DetailEvents
import kotlinx.coroutines.launch

@Composable
fun CryptoLineChartManualScroll(
    dataPoints: List<Float>,
    visiblePoints: Int = 10,
    pointWidth: Float = 130f,
    chartHeightDp: Float = 200f,
    goToEndTrigger: Boolean = true,
    onGoToEndConsumed: () -> Unit = {},
    onAutoScrollClicked: (DetailEvents.OnAutoScrollClicked) -> Unit ,
    isAutoScrollEnabled: Boolean = false
) {
    val scrollStateX = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()

    var targetYOffset by remember { mutableStateOf(0f) }
    val animatedYOffset by animateFloatAsState(
        targetValue = targetYOffset,
        animationSpec = tween(durationMillis = 300),
        label = "yCamera"
    )

    val yPositions = remember { mutableStateListOf<Float>() }

    var selectedPrice by remember { mutableStateOf<Float?>(null) }
    var selectedX by remember { mutableStateOf(0f) }
    var selectedY by remember { mutableStateOf(0f) }

    LaunchedEffect(dataPoints.size) {
        if (dataPoints.size > yPositions.size) {
            val lastIndex = dataPoints.size - 1
            val lastPrice = dataPoints[lastIndex]

            val newY = if (lastIndex == 0) {
                chartHeightDp / 2
            } else {
                val prevPrice = dataPoints[lastIndex - 1]
                val prevY = yPositions.lastOrNull() ?: chartHeightDp / 2
                val deltaPrice = lastPrice - prevPrice
                val deltaY = deltaPrice * 3f
                prevY - deltaY
            }
            if (!newY.isNaN()) {
                yPositions.add(newY.coerceIn(0f, chartHeightDp))
            }
        }
    }

    LaunchedEffect(dataPoints.size, isAutoScrollEnabled) {
        if (dataPoints.isNotEmpty() && isAutoScrollEnabled) {
            if (dataPoints.size > visiblePoints) {
                val scrollToX = ((dataPoints.size - visiblePoints) * pointWidth).toInt()
                coroutineScope.launch { scrollStateX.animateScrollTo(scrollToX) }
            }
            val lastY = yPositions.lastOrNull() ?: chartHeightDp / 2
            val newTargetYOffset = chartHeightDp / 2 - lastY
            if (!newTargetYOffset.isNaN()) targetYOffset = newTargetYOffset
        }
    }

    LaunchedEffect(goToEndTrigger) {
        if (goToEndTrigger) {
            val scrollToX = ((dataPoints.size - visiblePoints) * pointWidth).toInt()
            coroutineScope.launch { scrollStateX.animateScrollTo(scrollToX) }
            onAutoScrollClicked(DetailEvents.OnAutoScrollClicked(true))
            onGoToEndConsumed()
        }
    }

    val canvasWidth = maxOf(visiblePoints * pointWidth, dataPoints.size * pointWidth)

    Box(
        modifier = Modifier.padding(vertical = 16.dp)
            .fillMaxWidth()
            .height(chartHeightDp.dp)
            .background(Color.Black)
            .horizontalScroll(scrollStateX)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { onAutoScrollClicked(DetailEvents.OnAutoScrollClicked(false)) },
                    onDrag = { _, dragAmount ->
                        coroutineScope.launch { scrollStateX.scrollBy(-dragAmount.x) }
                        targetYOffset += dragAmount.y
                    }
                )
            }
    ) {
        Canvas(
            modifier = Modifier
                .width(canvasWidth.dp)
                .fillMaxHeight()
                .padding(10.dp)
                .pointerInput(Unit) {
                    detectTapGestures { offset ->
                        val maxIndex = minOf(yPositions.size, dataPoints.size) - 1
                        if (maxIndex >= 0) {
                            val index = ((offset.x + scrollStateX.value) / pointWidth).toInt()
                                .coerceIn(0, maxIndex)
                            selectedPrice = dataPoints.getOrNull(index)
                            selectedX = index * pointWidth
                            selectedY = yPositions.getOrNull(index)?.coerceIn(0f, chartHeightDp) ?: chartHeightDp / 2
                        }
                    }
                }
        ) {
            if (yPositions.size < 2) return@Canvas

            for (i in 0 until yPositions.size - 1) {
                val x1 = i * pointWidth
                val x2 = (i + 1) * pointWidth
                val y1 = yPositions[i] + animatedYOffset
                val y2 = yPositions[i + 1] + animatedYOffset

                drawLine(
                    color = Color(0xFFFFA500),
                    start = Offset(x1, y1),
                    end = Offset(x2, y2),
                    strokeWidth = 3f
                )
            }


            val lastX = (yPositions.size - 1) * pointWidth
            val lastY = yPositions.lastOrNull()?.plus(animatedYOffset) ?: chartHeightDp / 2
            drawCircle(Color(0xFF7A4102), radius = 8f, center = Offset(lastX, lastY))
            drawCircle(Color(0xFF7A4102), radius = 10f, center = Offset(lastX, lastY), style = Stroke(2f))


        }
    }
}
