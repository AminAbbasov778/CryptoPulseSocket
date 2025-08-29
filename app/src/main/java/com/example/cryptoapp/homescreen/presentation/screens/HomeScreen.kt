package com.example.cryptoapp.homescreen.presentation.screens

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cryptoapp.R
import com.example.cryptoapp.homescreen.presentation.events.HomeEvents
import com.example.cryptoapp.homescreen.presentation.navigation.HomeNavigation
import com.example.cryptoapp.homescreen.presentation.state.HomeState
import com.example.cryptoapp.ui.theme.Gray
import com.example.cryptoapp.ui.theme.Green
import com.example.cryptoapp.ui.theme.LightDark
import com.example.cryptoapp.ui.theme.Medium16
import com.example.cryptoapp.ui.theme.Medium28
import com.example.cryptoapp.ui.theme.Red
import com.example.cryptoapp.ui.theme.Regular14
import com.example.cryptoapp.ui.theme.White

@Composable
fun HomeScreen(state : HomeState, onEvent: (HomeEvents) -> Unit) {



    Scaffold { paddingValues ->
        Spacer(modifier = Modifier.height(39.dp))

        Column(
            modifier = Modifier

                .fillMaxSize()
                .background(color = Color.Black)
                .systemBarsPadding()
                .padding(paddingValues)
        ) {
            Text(
                text = stringResource(R.string.markets),
                modifier = Modifier.padding(start = 16.dp),
                color = White,
                style = Medium28
            )
            Spacer(modifier = Modifier.height(12.dp))

            Box(modifier = Modifier
                .height((0.7).dp)
                .fillMaxWidth()
                .background(color = LightDark))

            LazyColumn(modifier = Modifier.padding(start = 16.dp, top = 24.dp, end = 16.dp)) {
                items(state.cryptoList,key = {it.cryptoName}){crypto ->
                    val name =  crypto.cryptoName

                    Column{
                        Spacer(modifier = Modifier.height(12.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth().clickable {
                               onEvent(HomeEvents.Navigation(HomeNavigation.HomeScreenToDetailScreen(name)))
                            },
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.weight(1f)
                            ) {
                                Icon(
                                    painter = painterResource(crypto.cryptoIcon),
                                    contentDescription = "cryptoicon",
                                    tint = Color.Unspecified,
                                    modifier = Modifier.clip(RoundedCornerShape(100.dp))
                                )

                                Spacer(modifier = Modifier.width(8.dp))

                                Column {
                                    Text(
                                        text = crypto.cryptoName,
                                        lineHeight = 20.sp,
                                        style = Medium16,
                                        color = White
                                    )
                                    Text(
                                        text = crypto.cryptoFullName,
                                        lineHeight = 18.sp,
                                        style = Regular14,
                                        color = Gray
                                    )
                                }
                            }

                            if (crypto.changeIcon != null && crypto.percentChange.value != 0.0) {
                                Icon(
                                    painter = painterResource(crypto.changeIcon),
                                    contentDescription = "cryptostat",
                                    tint = Color.Unspecified,
                                    modifier = Modifier
                                        .weight(1f)
                                        .align(Alignment.CenterVertically)
                                )
                            } else {
                                Icon(
                                    painter = painterResource(R.drawable.ic_line),
                                    contentDescription = "cryptostat",
                                    tint = Color.Yellow,
                                    modifier = Modifier
                                        .weight(1f)
                                        .align(Alignment.CenterVertically)
                                )
                            }



                            Column(
                                horizontalAlignment = Alignment.End,
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = crypto.cryptoPrice.toString(),
                                    lineHeight = 20.sp,
                                    style = Medium16,
                                    color = White
                                )

                                val percentText = when {
                                    crypto.percentChange.value > 0 -> "${crypto.percentChange.value}%"
                                    crypto.percentChange.value < 0 -> "${crypto.percentChange.value}%"
                                    else -> "${crypto.percentChange.value}%" // 0 üçün, heç bir işarə
                                }

                                val percentColor = when {
                                    crypto.percentChange.value > 0 -> Green
                                    crypto.percentChange.value < 0 -> Red
                                    else -> Color.Yellow
                                }

                                Text(
                                    text = percentText,
                                    lineHeight = 16.sp,
                                    style = Regular14,
                                    color = percentColor
                                )

                            }
                        }
                        }

                        Spacer(modifier = Modifier.height(13.dp))

                        if(state.cryptoList.indexOf(crypto) != state.cryptoList.size - 1){
                            Box(
                                modifier = Modifier
                                    .height((0.7).dp)
                                    .fillMaxWidth()
                                    .background(color = LightDark)
                            )
                        }



                    }
                }

            }
        }

    }


