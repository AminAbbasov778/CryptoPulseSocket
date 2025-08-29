package com.example.cryptoapp.detailscreen.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.cryptoapp.data.presentation.models.CryptoHistoryUi
import com.example.cryptoapp.ui.theme.Gray
import com.example.cryptoapp.ui.theme.Green
import com.example.cryptoapp.ui.theme.Red
import com.example.cryptoapp.ui.theme.Regular12
import com.example.cryptoapp.ui.theme.Regular14
import com.example.cryptoapp.ui.theme.Regular16
import com.example.cryptoapp.ui.theme.White

@Composable
fun HistoryRow(history: CryptoHistoryUi) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = history.hour, style = Regular16, color = White)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = history.date, style = Regular12, color = Gray)
        }

        val percentText = when {
            history.percentChange.value > 0 -> "+${history.percentChange.value}%"
            history.percentChange.value < 0 -> "${history.percentChange.value}%"
            else -> "${history.percentChange.value}%"
        }
        val percentColor = when {
            history.percentChange.value > 0 -> Green
            history.percentChange.value < 0 -> Red
            else -> Color.Yellow
        }


        Text(
            text = percentText,
            style = Regular14,
            color = percentColor,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.End
        )


    }
}
