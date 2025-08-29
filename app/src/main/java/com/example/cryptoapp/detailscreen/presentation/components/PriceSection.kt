package com.example.cryptoapp.detailscreen.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cryptoapp.R
import com.example.cryptoapp.data.presentation.models.CryptoUiModel
import com.example.cryptoapp.ui.theme.Bold14
import com.example.cryptoapp.ui.theme.Bold28
import com.example.cryptoapp.ui.theme.Green
import com.example.cryptoapp.ui.theme.Red
import com.example.cryptoapp.ui.theme.White
import kotlin.math.abs

@Composable
fun PriceSection(coin: CryptoUiModel, onAutoScroll: () -> Unit) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(text = coin.cryptoName, style = Bold14, color = White)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "$${coin.cryptoPrice}", style = Bold28, color = White)
        Spacer(modifier = Modifier.height(10.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            val iconRotation = when {
                coin.percentChange.value > 0 -> 0f
                coin.percentChange.value < 0 -> 90f
                else -> 45f
            }
            val iconColor = when {
                coin.percentChange.value > 0 -> Green
                coin.percentChange.value < 0 -> Red
                else -> Color.Yellow
            }

            Icon(
                painter = painterResource(id = R.drawable.ic_increasing_sign),
                tint = iconColor,
                contentDescription = null,
                modifier = Modifier.rotate(iconRotation)
            )

            Spacer(modifier = Modifier.width(4.dp))

            val percentText = when {
                coin.percentChange.value > 0 -> "+${coin.priceDifference}  (${abs(coin.percentChange.value) }%)"
                coin.percentChange.value < 0 -> "-${coin.priceDifference}  (${abs(coin.percentChange.value) }%)"
                else -> "${coin.priceDifference}  (${abs(coin.percentChange.value) }%)"
            }

            val percentColor = when {
                coin.percentChange.value > 0 -> Green
                coin.percentChange.value < 0 -> Red
                else -> Color.Yellow
            }
            Text(
                text = percentText,
                style = Bold14,
                lineHeight = 20.sp,
                color = percentColor
            )

            Icon(
                imageVector = Icons.Default.ArrowForward,
                tint = White,
                contentDescription = null,
                modifier = Modifier.clickable { onAutoScroll() }
            )
        }
    }
}
