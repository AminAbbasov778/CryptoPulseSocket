package com.example.cryptoapp.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.cryptoapp.R

val Satoshi = FontFamily(
    Font(R.font.satoshi_regular, FontWeight.Normal),
    Font(R.font.satoshi_medium, FontWeight.Medium),
)

val Medium28 =
    TextStyle(fontSize = 28.sp, fontFamily = Satoshi, fontWeight = FontWeight.Medium)
 val Medium16 =   TextStyle(fontSize = 16.sp, fontFamily = Satoshi, fontWeight = FontWeight.Medium)
 val Medium14 =   TextStyle(fontSize = 14.sp, fontFamily = Satoshi, fontWeight = FontWeight.Medium)
 val Medium12 =   TextStyle(fontSize = 12.sp, fontFamily = Satoshi, fontWeight = FontWeight.Medium)
 val Regular14 =   TextStyle(fontSize = 14.sp, fontFamily = Satoshi, fontWeight = FontWeight.Normal)
 val Regular12 =   TextStyle(fontSize = 12.sp, fontFamily = Satoshi, fontWeight = FontWeight.Normal)
 val Regular16 =   TextStyle(fontSize = 16.sp, fontFamily = Satoshi, fontWeight = FontWeight.Normal)
 val Bold18 =   TextStyle(fontSize = 18.sp, fontFamily = Satoshi, fontWeight = FontWeight.Bold)
 val Bold28 =   TextStyle(fontSize = 28.sp, fontFamily = Satoshi, fontWeight = FontWeight.Bold)
 val Bold14 =   TextStyle(fontSize = 14.sp, fontFamily = Satoshi, fontWeight = FontWeight.Bold)
 val Bold16 =   TextStyle(fontSize = 16.sp, fontFamily = Satoshi, fontWeight = FontWeight.SemiBold)