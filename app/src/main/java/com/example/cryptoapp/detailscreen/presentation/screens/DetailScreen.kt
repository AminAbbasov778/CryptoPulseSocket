package com.example.cryptoapp.detailscreen.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cryptoapp.R
import com.example.cryptoapp.detailscreen.presentation.components.CryptoLineChartManualScroll
import com.example.cryptoapp.detailscreen.presentation.components.DetailTopBar
import com.example.cryptoapp.detailscreen.presentation.components.HistoryRow
import com.example.cryptoapp.detailscreen.presentation.components.PriceSection
import com.example.cryptoapp.detailscreen.presentation.events.DetailEvents
import com.example.cryptoapp.detailscreen.presentation.navigation.DetailNavigation
import com.example.cryptoapp.detailscreen.presentation.state.DetailState
import com.example.cryptoapp.ui.theme.Bold14
import com.example.cryptoapp.ui.theme.Bold16
import com.example.cryptoapp.ui.theme.Bold18
import com.example.cryptoapp.ui.theme.Bold28
import com.example.cryptoapp.ui.theme.Dark
import com.example.cryptoapp.ui.theme.DarkOrange200
import com.example.cryptoapp.ui.theme.Gray
import com.example.cryptoapp.ui.theme.Green
import com.example.cryptoapp.ui.theme.Red
import com.example.cryptoapp.ui.theme.Regular12
import com.example.cryptoapp.ui.theme.Regular14
import com.example.cryptoapp.ui.theme.Regular16
import com.example.cryptoapp.ui.theme.White
import kotlin.math.abs

@Composable
fun DetailScreen(state: DetailState, onEvents: (DetailEvents) -> Unit, isLoading: Boolean) {
    val listState = rememberLazyListState()
    val chartHeight = LocalConfiguration.current.screenHeightDp.dp / 3

    Scaffold { paddingValues ->
        if (isLoading) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize().background(Dark)
            ) {
                CircularProgressIndicator(color = DarkOrange200, strokeWidth = 6.dp)
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
                    .systemBarsPadding()
                    .padding(paddingValues)
            ) {
                Spacer(modifier = Modifier.height(13.dp))
                DetailTopBar {
                    onEvents(DetailEvents.Navigation(DetailNavigation.DetailScreenFromHomeScreen))
                }
                Spacer(modifier = Modifier.height(28.dp))

                state.detailState?.let { coin ->
                    PriceSection(coin) {
                        onEvents(DetailEvents.OnAutoScrollClicked(true))
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    CryptoLineChartManualScroll(
                        dataPoints = coin.cryptoHistory.map { it.price.toFloat() },
                        goToEndTrigger = state.goToEnd,
                        onGoToEndConsumed = { onEvents(DetailEvents.OnGoToEndClicked(true)) },
                        chartHeightDp = chartHeight.value,
                        isAutoScrollEnabled = state.isAutoScrollEnabled,
                        onAutoScrollClicked = { onEvents(DetailEvents.OnAutoScrollClicked(it.isAutoScrollEnabled)) }
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = stringResource(R.string.history),
                        style = Bold16,
                        color = White,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth(),
                        state = listState,
                        reverseLayout = true
                    )
                        {
                            items(coin.cryptoHistory) { history ->

                                HistoryRow(history)
                            }
                        }


                    LaunchedEffect(coin.cryptoHistory.size) {
                        if (coin.cryptoHistory.isNotEmpty()) {
                            listState.animateScrollToItem(coin.cryptoHistory.size - 1) // reverseLayout = true olduğuna görə son elementi göstərir
                        }
                    }
                }
            }
        }
    }}
